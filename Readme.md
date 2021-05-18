So when you debug the XmlAdapter, you see exactly how it works:
Every <> will get treated as starting element, and every </> as closing element
and /n also gets treated as an element, but there is no logic for it.

-> create an item object
-> check the html tag ->  startElement.getName().getLocalPart() gives u the tag name
-> then switch case to set the property of the item object
-> check the closing tag, if closing tag, and startElement.getName().getLocalPart()
    -> you know item is finished -> add item to a list

simple


to do it in large scale -> you have xsd -> create class from xsd (model class)
then go parse