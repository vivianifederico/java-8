import model.Gender;
import model.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sample {

    private static List<Person> createPeople() {
        return Arrays.asList(
                new Person("Sara", Gender.FEMALE, 20),
                new Person("Sara", Gender.FEMALE, 22),
                new Person("Bob", Gender.MALE, 20),
                new Person("Paula", Gender.FEMALE, 32),
                new Person("Paul", Gender.MALE, 32),
                new Person("Jack", Gender.MALE, 2),
                new Person("Jack", Gender.MALE, 72),
                new Person("Jill", Gender.FEMALE, 12)
        );
    }

    public static void main(String[] args) {
        List<Person> people = createPeople();
        System.out.println(people);
        List<String> nameUpperCase = people.stream()
                .filter(person -> person.getAge() > 18)
                .map(Person::getName)
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println(nameUpperCase);

        people
                .stream()
                .filter(person -> person.getGender().equals(Gender.MALE))
                .map(person -> new Person(person.getName().toUpperCase(), person.getGender(), person.getAge()))
                .forEach(System.out::println);

        Integer reduce = people.stream()
                .mapToInt(Person::getAge)
//                .reduce(0, (carry, age) -> carry + age);
                .sum();
        System.out.println(reduce);


        System.out.println(people.stream()
                .filter(person -> person.getAge() < 18)
                .count());


        List<String> names = new ArrayList<>();
        people.stream()
                .filter(person -> person.getAge() > 17)
                .map(person -> person.getName().toUpperCase())
                .forEach(names::add);


        //supplier
        List<String> names2 =
                people.stream()
                        .filter(person -> person.getAge() > 17)
                        .map(person -> person.getName().toUpperCase())
                        .collect(
                                ArrayList::new,
                                (list, name) -> list.add(name),
                                (list1, list2) -> list1.addAll(list2)
                        );

        System.out.println(names2);

        Set<String> maleNames = people.stream()
                .filter(person -> (Gender.MALE).equals(person.getGender()))
                .map(Person::getName)
                .collect(Collectors.toSet());

        System.out.println(maleNames);

//Map
        Map<String, Person> map = people.stream()
                .collect(Collectors.toMap(
                        person -> person.getName() + ":" + person.getAge(),
                        person -> person
                ));

        System.out.println(map);


        //Map of list value

        Map<String, List<Person>> mapOfList = people.stream()
                .filter(person -> person.getAge() > 18)
                .collect(Collectors.groupingBy(Person::getName));

        System.out.println("map of List");
        System.out.println(mapOfList);

        //find the first person whose name is four letters but is older than 25

        Optional<Person> firstPersonLenght4Older25 = people.stream()
                .filter(person -> (person.getName().length() == 4))
                .filter(person -> person.getAge() > 25)
                .findFirst();

        System.out.println(firstPersonLenght4Older25.get());


        //parallel
        Stream.iterate(1, e -> e + 1)
                .filter(e -> e % 2 == 0)
                .limit(10)
                .forEach(System.out::println);

    }

}

