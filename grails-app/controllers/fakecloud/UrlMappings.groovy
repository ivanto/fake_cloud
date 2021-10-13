package fakecloud

class UrlMappings {

    static mappings = {

        "/api/machines"(resources:"machine")

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
