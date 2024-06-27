package com.bygit.e_com;

import com.bygit.e_com.dao.CategoryRepository;
import com.bygit.e_com.dao.ProductRepository;
import com.bygit.e_com.entities.Category;
import com.bygit.e_com.entities.Product;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Random;

@SpringBootApplication
public class EComApplication implements CommandLineRunner {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(EComApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		repositoryRestConfiguration.exposeIdsFor(Product.class, Category.class);

		categoryRepository.save(new Category(null,"Computers",null,null,null));
		categoryRepository.save(new Category(null,"Printers",null,null,null));
		categoryRepository.save(new Category(null,"Smart phones",null,null,null));
		Random rnd=new Random();
		categoryRepository.findAll().forEach(c->{
			for (int i = 0; i <10 ; i++) {
				Product p=new Product();
				int length = 10;
				String randomString = RandomStringUtils.randomAlphanumeric(length);
				p.setName(randomString);
				p.setCurrentPrice(100+rnd.nextInt(10000));
				p.setAvailable(rnd.nextBoolean());
				p.setPromotion(rnd.nextBoolean());
				p.setSelected(rnd.nextBoolean());
				p.setCategory(c);
				p.setPhotoName("unknown.png");
				productRepository.save(p);
			}
		});

	}
}
