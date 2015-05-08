<%@ page import="me.nimavat.Book" %>



<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="book.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${bookInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'author', 'error')} required">
	<label for="author">
		<g:message code="book.author.label" default="Author" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="author" name="author.id" from="${me.nimavat.Author.list()}" optionKey="id" required="" value="${bookInstance?.author?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'price', 'error')} required">
	<label for="price">
		<g:message code="book.price.label" default="Price" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="price" value="${fieldValue(bean: bookInstance, field: 'price')}" required=""/>

</div>

