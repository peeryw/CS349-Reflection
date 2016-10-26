package Introspection_Reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import acme.NetworkService;

public class Driver {
	
    public static void main(String[] args) throws NoSuchFieldException, SecurityException, 
    IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
    	// Create the object that will be accessed via reflection
    	NetworkService target = new NetworkService();
    	
    	Class reflectionTargetClass = target.getClass();
    	
    	Field fields[] = reflectionTargetClass.getDeclaredFields();
    	System.out.println("\nFields:");
    	
	    for (Field field : fields) {
	    	System.out.println(field.getName() + " is: "
	    			+ field.toGenericString());
	    }
	    
	    Field notSecretStringAttribute = reflectionTargetClass.getDeclaredField("machineName");
	    // The filed is private. In order to read/write the value we have to change accessibility to public.
	    notSecretStringAttribute.setAccessible(true);
	    
	    String notSecretStringValue = (String)notSecretStringAttribute.get(target);
	    
	    System.out.println("String attribute value is: " + notSecretStringValue);
	    
	    notSecretStringAttribute.set(target, "aws.com");
	    
	    System.out.println("String attribute value is: " + notSecretStringValue);
	    
        //NetworkService ns = new NetworkService();
        target.connect();
    }
}



