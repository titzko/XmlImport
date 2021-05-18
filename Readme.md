App watches a certain directory for new xml files.
App can parse different types of xml files and saves them as pojo in a database. Easy to extend. Create Entity, Mapper and add those classes to MapperRegistry.
Your Xml-files need to have a MessageType-Tag, such that correct Mapper gets picked. Just check other xml-files for reference in ressources.




