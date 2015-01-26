package com.peerlez.authorize.db;


/**
 * Class to contain parameters for stored procedure SQL function call.
 * 
 * @author A.Sillanpaa
 *
 */
public class SqlParameters {

   private int _index;
   private Object _value;


   public SqlParameters(final int parameterIndex, final Object value) {
       _index = parameterIndex;
       _value = value;
   }

   /**
    * Returns the parameter index in the stored procedure parameters list
    * @return the value of the parameter index
    */
   public int getIndex() {
       return _index;
   }

   /**
    * Returns the parameter value
    * @return the value of the parameter
    */
   public Object getValue() {
       return _value;
   }
}
