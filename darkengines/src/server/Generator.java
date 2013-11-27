package server;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;

import org.hibernate.Session;
import org.hibernate.Transaction;

import darkengines.database.DBSessionFactory;

public class Generator {
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		
		Session session = DBSessionFactory.GetSession();
		
		Set<ProgrammingLanguage> pl = new HashSet<ProgrammingLanguage>(session.createCriteria(ProgrammingLanguage.class).list());
		Set<Framework> f = new HashSet<Framework>(session.createCriteria(Framework.class).list());
		Set<Language> l = new HashSet<Language>(session.createCriteria(Language.class).list());
		
		int i = 89;
		Transaction transaction = session.beginTransaction();
		while (i<300) {
		int rnd = (int) (Math.round(Math.random()*1000)+1000);
		User user = new User();
		user.setEmail(String.format("rivarol%d@hotmail.com", i));
		user.setPassword(User.hashPassword("test"));
		user.setType(UserType.Dev);
		Profile profile = new Profile();
		profile.setDiploma(5);
		profile.setSeniority(5);
		
		InputStream in = new FileInputStream(String.format("J:\\aus\\IMG_%d.JPG", rnd));
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
		byte[] bytes = baos.toByteArray();
		baos.close();
		
		profile.setPhoto(bytes);
		
		profile.setProgrammingLanguages(randomSet(pl, 10));
		profile.setFrameworks(randomSet(f, 10));
		profile.setLanguages(randomSet(l, 10));
		
		session.save(profile);
		
		user.setProfile(profile);
		
		Identity identity = new Identity();
		identity.setAddress("40 rue Baudin");
		identity.setBirthDate(new Date());
		identity.setFirstName("Florent");
		identity.setLastName("Tollin de Rivarol");
		identity.setPhone("0669788311");
		
		session.save(identity);
		
		user.setIdentity(identity);
		

		session.save(user);
		session.flush();
		i++;
		}
		transaction.commit();
		session.close();
		
	}
	
	public static <T> Set<T> randomSet(Set<T> set, int count) {
		int length = set.size();
		Set<T> result = new HashSet<T>();
		T[] array = (T[])set.toArray();
		int i = 0;
		while (i < count) {
			int rnd = getRandom(0, length-1);
			result.add(array[rnd]);
			i++;
		}
		return result;
	}
	public static int getRandom(int min, int max) {
		if (Generator.rand == null) {
			Generator.rand = new Random();
		}
		return (int)Math.round(Generator.rand.nextDouble() * (max-min))+min;
	}
	public static Random rand = null;
}
