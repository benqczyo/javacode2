package com.benqcz.xml.test;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.benqcz.xml.utils.Dom4jUtils;

import junit.framework.Assert;

public class TestDom4j {
	
	public Document document;
	public Element root;
	public boolean isModify;
	
	@Before
	public void enter() {
		document = Dom4jUtils.open();
		root = document.getRootElement();
		isModify = false;
	}
	
	@After
	public void quit() {
		if (isModify) Dom4jUtils.save(document);
		document = null;
		root = null;
		isModify = false;
	}
	
	@Test
	public void getTheAuthorOfSecondBook() {
		Element secondBook = (Element) root.elements("��").get(1);
		Element author = secondBook.element("����");
		Assert.assertEquals("����", author.getTextTrim());
	}
	
	@Test
	public void listAllElments() {
		treeWalk(root, "");
	}

	private void treeWalk(Element element, String deep) {
		if (element.getNodeType() == Element.ELEMENT_NODE) {
			System.out.println(deep + element.getName());
			List<Element> elements = element.elements();
			for (Element e : elements)
				treeWalk(e, deep + "\t");
		}
	}
	
	@Test
	public void modifyThePriceOfSecondBook() {
		isModify = true;
		Element secondBook = (Element) root.elements("��").get(1);
		Element price = secondBook.element("�ۼ�");
		price.setText("1000");
	}
}