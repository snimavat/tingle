package me.nimavat

import grails.plugin.nimble.auth.NimbleAuth

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AuthorController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @NimbleAuth(role="ADMIN")
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Author.list(params), model:[authorInstanceCount: Author.count()]
    }

    def show(Author authorInstance) {
        respond authorInstance
    }

    def create() {
        respond new Author(params)
    }

    @Transactional
    def save(Author authorInstance) {
        if (authorInstance == null) {
            notFound()
            return
        }

        if (authorInstance.hasErrors()) {
            respond authorInstance.errors, view:'create'
            return
        }

        authorInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'author.label', default: 'Author'), authorInstance.id])
                redirect authorInstance
            }
            '*' { respond authorInstance, [status: CREATED] }
        }
    }

    def edit(Author authorInstance) {
        respond authorInstance
    }

    @Transactional
    def update(Author authorInstance) {
        if (authorInstance == null) {
            notFound()
            return
        }

        if (authorInstance.hasErrors()) {
            respond authorInstance.errors, view:'edit'
            return
        }

        authorInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Author.label', default: 'Author'), authorInstance.id])
                redirect authorInstance
            }
            '*'{ respond authorInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Author authorInstance) {

        if (authorInstance == null) {
            notFound()
            return
        }

        authorInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Author.label', default: 'Author'), authorInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'author.label', default: 'Author'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
