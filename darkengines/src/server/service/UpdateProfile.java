package server.service;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import server.Diploma;
import server.Framework;
import server.Language;
import server.Profile;
import server.ProgrammingLanguage;
import server.User;
import server.UserType;
import server.model.ProfileInput;
import server.model.ProfileOutput;
import darkengines.database.DBSessionFactory;
import darkengines.service.JSonService;

@SuppressWarnings("serial")
public class UpdateProfile extends JSonService<ProfileInput, ProfileOutput> {

	@Override
	public Class<ProfileInput> getInputType() {
		return ProfileInput.class;
	}

	@Override
	public Class<ProfileOutput> getOutputType() {
		return ProfileOutput.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ProfileOutput processJsonRequest(ProfileInput data) throws Exception {
		User user = Util.getUserByToken(data.getToken());
		if (user == null) {
			throw new Exception("token.invalid");
		}
		if (user.getType() != UserType.Dev) {
			throw new Exception("user.type.invalid");
		}
		Profile profile = user.getProfile();
		if (profile == null) {
			profile = new Profile();
		}
		Session session = DBSessionFactory.GetSession();
		List<Long> programmingLanguageIds = data.getProgrammingLanguageIds();
		List<Long> frameworkIds = data.getFrameworkIds();
		List<Long> languageIds = data.getLanguageIds();
		if (programmingLanguageIds != null && programmingLanguageIds.size()>0) {
			List<ProgrammingLanguage> programmingLanguages = session.createCriteria(ProgrammingLanguage.class)
					.add(Restrictions.in("id", programmingLanguageIds))
					.list();
			profile.getProgrammingLanguages().clear();
			profile.getProgrammingLanguages().addAll(programmingLanguages);
		}
		if (frameworkIds != null && frameworkIds.size()>0) {
			List<Framework> frameworks = session.createCriteria(Framework.class)
					.add(Restrictions.in("id", frameworkIds))
					.list();
			profile.getFrameworks().clear();
			profile.getFrameworks().addAll(frameworks);
		}
		if (languageIds != null && languageIds.size()>0) {
			List<Language> languages = session.createCriteria(Language.class)
					.add(Restrictions.in("id", languageIds))
					.list();
			profile.getLanguages().clear();
			profile.getLanguages().addAll(languages);
		}
		profile.setDiploma(data.getDiploma());
		profile.setSeniority(data.getSeniority());
		if (data.getPhoto() != null && !data.getPhoto().isEmpty()) {
			Base64 codec = new Base64();
			byte[] bytes = codec.decodeBase64(data.getPhoto().split(",")[1]);
			
			InputStream in = new ByteArrayInputStream(bytes);
			BufferedImage image = ImageIO.read(in);
			int height = image.getHeight();
			int width = image.getWidth();
			int x = 0;
			int y = 0;
			int side = 0;
			float ratio = (float)width/(float)height;
			if (height >= width) {
				y = (height - width)/2;
				side = width;
			}
			if (height <= width) {
				x = (width - height)/2;
				side = height;
			}
			BufferedImage resizedImage = new BufferedImage(128, 128, BufferedImage.TYPE_INT_ARGB);
			
			Graphics2D g = resizedImage.createGraphics();
			g.drawImage(image, 0, 0, 128, 128, x, y, x+side, y+side, null);
			g.dispose();
			g.setComposite(AlphaComposite.Src);
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(resizedImage, "png", baos);
			baos.flush();
			bytes = baos.toByteArray();
			baos.close();
			
			profile.setPhoto(bytes);
		}
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(profile);
		user.setProfile(profile);
		session.saveOrUpdate(user);
		session.flush();
		transaction.commit();
		session.close();
		
		return null;
	}

}
