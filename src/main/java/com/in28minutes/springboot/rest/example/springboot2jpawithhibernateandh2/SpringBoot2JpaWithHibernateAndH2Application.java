package com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2;

import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.lob.model.Photo;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.lob.model.User;
import com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.lob.service.UserService;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableTransactionManagement
@EntityScan(basePackages = "com.in28minutes.springboot.rest.example.springboot2jpawithhibernateandh2.lob")
public class SpringBoot2JpaWithHibernateAndH2Application {

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(SpringBoot2JpaWithHibernateAndH2Application.class, args);

//        String[] beanDefinitionNames = context.getBeanDefinitionNames();
//        Arrays.stream(beanDefinitionNames).forEach(System.out::println);
        User newUser = new User();
        newUser.setName("User");

        InputStream inputStream1 = SpringBoot2JpaWithHibernateAndH2Application.class
                .getClassLoader()
                .getResourceAsStream("nature1.jpg");
        InputStream inputStream2 = SpringBoot2JpaWithHibernateAndH2Application.class
                .getClassLoader()
                .getResourceAsStream("nature2.jpg");
        if (inputStream1 == null && inputStream2 == null) {
            System.out.println("Unable to get resources");
        }
        Photo photo1 = new Photo();
        char[] chars = ReadFileToCharArray("src/main/resources/The Hunger Games1.txt");

        photo1.setBook(chars);
        photo1.setPhoto(IOUtils.toByteArray(inputStream1));
        Photo photo2 = new Photo();
        photo2.setBook(null);
        photo2.setPhoto(IOUtils.toByteArray(inputStream2));
        newUser.setPhotos(Arrays.asList(photo1, photo2));


        UserService userService = (UserService) context.getBean("userServiceImpl");

        userService.createOrUpdate(newUser);

        User user = userService.getUserByName("User");
        List<User> list = userService.getAll();

        System.out.println(list);
        System.out.println(user);

        assert user.getPhotos().size() == 2;

        InputStream targetStream1 = new ByteArrayInputStream(user.getPhotos().get(0).getPhoto());
        File targetFile1 = new File("src/main/resources/targetFile1.jpg");
        System.out.println(targetFile1.exists());
        FileUtils.copyInputStreamToFile(targetStream1, targetFile1);

        InputStream targetStream2 = new ByteArrayInputStream(user.getPhotos().get(1).getPhoto());
        File targetFile2 = new File("src/main/resources/targetFile2.jpg");
        System.out.println(targetFile2.exists());
        FileUtils.copyInputStreamToFile(targetStream2, targetFile2);

        charArrayToFile(user.getPhotos().get(0).getBook(),"src/main/resources/The Hunger Games_ext.txt");


    }


    public static char[] ReadFileToCharArray(String filePath) throws IOException {
        StringBuilder fileData = new StringBuilder(1000);
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        char[] buf = new char[10];
        int numRead = 0;
        while ((numRead = reader.read(buf)) != -1) {
            System.out.println(numRead);
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
            buf = new char[1024];
        }
        reader.close();
        return fileData.toString().toCharArray();
    }

    public static void charArrayToFile(char[] chars, String filePath) throws IOException {
        Path path = Paths.get(filePath);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(chars);
        }
    }

}
