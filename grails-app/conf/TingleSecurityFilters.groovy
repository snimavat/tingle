
import grails.plugin.nimble.core.AdminsService
import grails.plugin.nimble.security.NimbleFilterBase

class TingleSecurityFilters extends NimbleFilterBase {

	def filters = {

		// Content requiring users to be authenticated
		secure(controller: "book") {
			before = { accessControl { true } }
		}

	}
}
