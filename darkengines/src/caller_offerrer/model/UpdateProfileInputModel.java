package caller_offerrer.model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import caller_offerrer.Framework;
import caller_offerrer.Image;
import caller_offerrer.Language;
import caller_offerrer.Profile;
import caller_offerrer.ProgrammingLanguage;
import darkengines.database.DBSessionFactory;
import darkengines.image.ImageHelper;

public class UpdateProfileInputModel extends TokenizenModel {
	private Set<Long> programmingLanguageIds;
	private Set<Long> frameworkIds;
	private Set<Long> languageIds;
	private Integer diploma;
	private Integer seniority;
	private String photo;
	public Set<Long> getProgrammingLanguageIds() {
		return programmingLanguageIds;
	}
	public void setProgrammingLanguageIds(Set<Long> programmingLanguageIds) {
		this.programmingLanguageIds = programmingLanguageIds;
	}
	public Set<Long> getFrameworkIds() {
		return frameworkIds;
	}
	public void setFrameworkIds(Set<Long> frameworkIds) {
		this.frameworkIds = frameworkIds;
	}
	public Set<Long> getLanguageIds() {
		return languageIds;
	}
	public void setLanguageIds(Set<Long> languageIds) {
		this.languageIds = languageIds;
	}
	public Integer getDiploma() {
		return diploma;
	}
	public void setDiploma(Integer diplomaId) {
		this.diploma = diplomaId;
	}
	public Integer getSeniority() {
		return seniority;
	}
	public void setSeniority(Integer seniority) {
		this.seniority = seniority;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	@SuppressWarnings({ "unchecked", "rawtypes", "static-access" })
	public Profile mergeProfile(Profile profile) throws IOException {
		Session session = DBSessionFactory.GetSession();
		if (programmingLanguageIds != null) {
			profile.getProgrammingLanguages().clear();
			if (programmingLanguageIds.size() > 0) {
				Collection<ProgrammingLanguage> programmingLanguages = session.createCriteria(ProgrammingLanguage.class)
						.add(Restrictions.in("id", programmingLanguageIds))
						.list();
				profile.getProgrammingLanguages().addAll((Collection)programmingLanguages);
			}
		}
		if (frameworkIds != null) {
			profile.getFrameworks().clear();
			if (frameworkIds.size() > 0) {
				Set<Framework> frameworks = new HashSet<Framework>(session.createCriteria(Framework.class)
						.add(Restrictions.in("id", frameworkIds))
						.list());
				profile.getFrameworks().addAll((Collection)frameworks);
			}
		}
		if (languageIds != null) {
			profile.getLanguages().clear();
			if (languageIds.size() > 0) {
				Set<Language> languages = new HashSet<Language>(session.createCriteria(Language.class)
						.add(Restrictions.in("id", languageIds))
						.list());
				profile.getLanguages().addAll((Collection)languages);
			}
		}
		profile.setDiploma(diploma);
		profile.setSeniority(seniority);
		if (photo != null && !photo.isEmpty()) {
			Base64 codec = new Base64();
			byte[] bytes = codec.decodeBase64(photo.split(",")[1]);
			InputStream in = new ByteArrayInputStream(bytes);
			BufferedImage inputImage = ImageIO.read(in);
			BufferedImage outputImage = ImageHelper.resizeCenter(inputImage, 128, 128);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(outputImage, "png", baos);
			baos.flush();
			bytes = baos.toByteArray();
			baos.close();
			Image image = profile.getImage();
			image.setData(bytes);
		}
		session.close();
		return profile;
	}
}
