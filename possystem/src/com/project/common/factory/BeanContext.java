package com.project.common.factory;



/*
 * * @author Gopal
 * @version 1.0
 * @since 08 Aug 2012
 * 
 */


	import javax.faces.context.FacesContext;
	import javax.faces.el.VariableResolver;
	import org.apache.commons.logging.Log;
	import org.apache.commons.logging.LogFactory;


	@SuppressWarnings("deprecation")   
	public class BeanContext {
		
		private static Log log = LogFactory.getLog(BeanContext.class);
		
		public static Object getReference(String arg) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			VariableResolver vr = facesContext.getApplication()
					.getVariableResolver();
			return vr.resolveVariable(facesContext, arg);
		}	
		/*
		 * 
		 *  Release any
			resources associated with this FacesContext
			instance.  Faces implementations may choose to pool instances in
			the associated FacesContextFactory  to avoid repeated
			object creation and garbage collection.  After
			release() is called on a FacesContext
			instance (until the FacesContext instance has been
			recycled by the implementation for re-use), calling any other
			methods will cause an IllegalStateException to be
			thrown.
		 */
		public static void release(){     
			FacesContext facesContext = null;
			try{
				facesContext = FacesContext.getCurrentInstance();
				facesContext.release();
			} catch(Exception e){
				if(log.isErrorEnabled()){
					log.error("Error occurs @release()...", e);
				}
			} finally{
				facesContext = null;
			}
		}
	}




