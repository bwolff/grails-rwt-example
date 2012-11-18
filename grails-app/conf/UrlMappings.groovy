class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

        // Comment out this line and un-comment the line below to map the entry point to the base URL.
        "/"(view:"/index")
//        "/"(uri: "/hello")
		"500"(view:'/error')
	}
}
