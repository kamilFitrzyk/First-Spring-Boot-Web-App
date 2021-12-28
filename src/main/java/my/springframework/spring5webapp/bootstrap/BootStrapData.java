package my.springframework.spring5webapp.bootstrap;

import my.springframework.spring5webapp.domain.Author;
import my.springframework.spring5webapp.domain.Book;
import my.springframework.spring5webapp.domain.Publisher;
import my.springframework.spring5webapp.repositories.AuthorRepository;
import my.springframework.spring5webapp.repositories.BookRepositories;
import my.springframework.spring5webapp.repositories.PublishRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepositories bookRepositories;
    private final PublishRepository publisherRepository;


    public BootStrapData(AuthorRepository authorRepository, BookRepositories bookRepositories, PublishRepository publishRepository) {
        this.authorRepository = authorRepository;
        this.bookRepositories = bookRepositories;
        this.publisherRepository = publishRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap");

        Publisher publisher = new Publisher();
        publisher.setName("SFG Publishing");
        publisher.setCity("St Petersburg");
        publisher.setState("FL");

        publisherRepository.save(publisher);

        System.out.println("Publisher Count: " + publisherRepository.count());

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design","123123");
        eric.getBooks().add(ddd);
        ddd.getAuthor().add(eric);

        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepositories.save(ddd);
        publisherRepository.save(publisher);

         Author rod = new Author("Rod","Johnson");
         Book noEJB = new Book("J2EE Development without EJB", "124124512");
         rod.getBooks().add(noEJB);
         noEJB.getAuthor().add(rod);

         noEJB.setPublisher(publisher);
         publisher.getBooks().add(noEJB);

         authorRepository.save(rod);
         bookRepositories.save(noEJB);
         publisherRepository.save(publisher);

        System.out.println("Number of Books: " +bookRepositories.count());
        System.out.println("Publisher Number of Books: " + publisher.getBooks().size());
    }
}
