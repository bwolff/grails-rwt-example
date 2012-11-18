package grails.rwt.example

class Foo {

    Long id
    String label
    String description

    static constraints = {
    }

    @Override
    public String toString() {
        return label
    }
}
