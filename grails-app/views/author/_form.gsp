<%@ page import="me.nimavat.Author" %>



<div class="fieldcontain ${hasErrors(bean: authorInstance, field: 'books', 'error')} ">
	<label for="books">
		<g:message code="author.books.label" default="Books" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${authorInstance?.books?}" var="b">
    <li><g:link controller="book" action="show" id="${b.id}">${b?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="book" action="create" params="['author.id': authorInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'book.label', default: 'Book')])}</g:link>
</li>
</ul>


</div>

<div class="fieldcontain ${hasErrors(bean: authorInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="author.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${authorInstance?.name}"/>

</div>

