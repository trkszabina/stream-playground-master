package brickset;

import repository.Repository;

import java.util.Comparator;

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





    public static void main(String[] args) {

        var repository1 = new LegoSetRepository();
        System.out.println(repository1.countLegoSetsWithTheme("Games"));

        var repository2 = new LegoSetRepository();
        System.out.println(repository2.countLegoSetsWithPiecesOver450(450));

        var repository3 = new LegoSetRepository();
        repository3.printLegoSetsNameinAlphabeticalOrder();

        var repository4 = new LegoSetRepository();
        repository4.printFirst5LegoSetsName();

        var repository5 = new LegoSetRepository();
        System.out.println(repository5.printAverageLegoSetsPieces());

    }

}
