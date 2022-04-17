package brickset;

import repository.Repository;

import java.time.Year;
import java.util.Comparator;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents a repository of {@code LegoSet} objects.
 */
public class LegoSetRepository extends Repository<LegoSet> {

    public LegoSetRepository() {
        super(LegoSet.class, "brickset.json");
    }

    /**
     * Returns the number of LEGO sets with the theme specified
     *
     * @param theme a LEGO set theme
     * @return the number of LEGO sets with the theme specified
     */
    public long countLegoSetsWithTheme(String theme) {
        return getAll().stream()
                .filter(legoSet -> legoSet.getTheme() != null && legoSet.getTheme().contains(theme))
                .count();
    }

    /**
     * Returns the number of LEGO sets which are over the specified number
     *
     * @param number a LEGO set number
     * @return the number of LEGO sets which are over the specified number
     */
    public long countLegoSetsWithPiecesOver450(int number) {
        return getAll().stream()
                .filter(legoSet -> legoSet.getPieces()>number)
                .count();
    }

    /**
     * Prints every LEGO sets name in alphabetical order
     */
    public void printLegoSetsNameinAlphabeticalOrder() {
        getAll().stream().
                map(legoSet -> legoSet.getName()).
                sorted(Comparator.nullsFirst(Comparator.naturalOrder())).
                forEach(System.out::println);
    }

    /**
     * Prints the first five LEGO sets name
     */
    public void printFirst5LegoSetsName() {
        getAll().stream().
                map(legoSet -> legoSet.getName()).
                limit(5).
                forEach(System.out::println);
    }

    /**
     * Returns the average number of LEGO sets pieces
     *
     * @return the average number of LEGO sets pieces
     */
    public double printAverageLegoSetsPieces() {
        return getAll().stream().
                mapToLong(legoSet -> legoSet.getPieces()).
                average().
                getAsDouble();
    }

    /**----------STREAM2----------------*/

     /**
      * Returns whether any set has 481 pieces.
      * *
      * @return whether any set has 481 pieces.
     */
    public boolean returnIfAnySetHas481Pieces() {
        return getAll().stream()
                .map(LegoSet::getPieces)
                .anyMatch(piece -> piece == 481);
    }


    /**
     * Prints the distinct tags
     */
    public void printAllDistinctTags() {
            getAll().stream()
                .filter(brickset -> brickset.getTags() != null)
                .flatMap(brickset ->  brickset.getTags().stream())
                .distinct()
                .forEach(System.out::println);
    }


    /**
     * Prints the max amount of Lego pieces
     */
    public void printMaxAmountOfLegoPieces() {
        getAll().stream()
                .map(LegoSet::getPieces)
                .reduce(Integer::max)
                .ifPresent(System.out::println);
    }

    /**
     * Returns a Map object, that contains a summary of the lego sets' packaging type and their frequency.
     *
     * @return {@code Map<String, Long>} object wrapping how many lego set has the same packaging type
     */
    public Map<PackagingType, Long> getNumberOfSetsForEachPackagingType() {
        return getAll().stream()
                .collect(Collectors.groupingBy(LegoSet::getPackagingType, Collectors.counting()));
    }

    /**
     * Returns a Map object, that contains every theme and their packaging type.
     *
     * @return {@code Map<String, Set<String>>} object wrapping the sets' themes and their packaging type.
     */
    public Map<String, Set<PackagingType>> getMapOfThemesWithTheirPackagingType() {
        return getAll().stream()
                .collect(Collectors.groupingBy(LegoSet::getTheme,
                        Collectors.mapping(LegoSet::getPackagingType,
                                Collectors.filtering(Objects::nonNull,
                                        Collectors.toSet()))));
    }



    public static void main(String[] args) {

        var repository1 = new LegoSetRepository();

        /** Stream1 házi
        System.out.println(repository1.countLegoSetsWithTheme("Games"));
        System.out.println(repository1.countLegoSetsWithPiecesOver450(450));
        repository1.printLegoSetsNameinAlphabeticalOrder();
        repository1.printFirst5LegoSetsName();
        System.out.println(repository1.printAverageLegoSetsPieces());
        */


        System.out.println(repository1.returnIfAnySetHas481Pieces());
        repository1.printAllDistinctTags();
        repository1.printMaxAmountOfLegoPieces();
        System.out.println(repository1.getNumberOfSetsForEachPackagingType());
        System.out.println(repository1.getMapOfThemesWithTheirPackagingType());
    }

}
