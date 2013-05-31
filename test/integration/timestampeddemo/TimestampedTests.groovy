package timestampeddemo

import static org.junit.Assert.*
import org.junit.*

class TimestampedTests {

   def fullTimestamped = new FullTimestamped()
   def creationTimestamped = new CreationTimestamped()
   def updateTimestamped = new UpdateTimestamped()
   def customClassTimestamped = new CustomClassTimestamped()
   
   @Test
   void testFullTimestamped() {
      checkTimestampedProperty(fullTimestamped, ['dateCreated': true, 'lastUpdated': true])
   }
   
   @Test
   void testCreationTimestamped() {
      checkTimestampedProperty(creationTimestamped, ['dateCreated': true, 'lastUpdated': false])
   }
   
   @Test
   void testUpdateTimestamped() {
      checkTimestampedProperty(updateTimestamped, ['dateCreated': false, 'lastUpdated': true])
   }
   
   @Test
   void testCustomClassTimestamped() {
      checkTimestampedProperty(customClassTimestamped, ['dateCreated': true, 'lastUpdated': true], 
         java.lang.Long)
   }

	private checkTimestampedProperty(Object domainClassInstance, 
         Map<String, Boolean> timestampProps, Class timestampClass = java.util.Date) {
      timestampProps.each { propertyName, shouldExist ->
         def persistentProperty = domainClassInstance.domainClass.persistentProperties.find { 
            it.name == propertyName && it.referencedPropertyType == timestampClass
         }
         if (shouldExist) {
            assert persistentProperty != null, "Property $propertyName not found in object $domainClassInstance"
         }
         else {
            assert persistentProperty == null, "Property $propertyName found in object $domainClassInstance"
         }
		}
	}
    
}
