import grails.rwt.example.Foo

class BootStrap {

    def init = { servletContext ->
        // Create some test data to be displayed in the UI.
        10.times {
            new Foo(id: it, label: "Foo object ${it}", description: "Description ${it}").save()
        }
    }

    def destroy = {
    }
}
