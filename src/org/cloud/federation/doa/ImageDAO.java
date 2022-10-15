package org.cloud.federation.doa;



import org.cloud.federation.dao.exceptions.NonexistentEntityException;
import org.cloud.federation.dao.exceptions.PreexistingEntityException;
//import com.miracl.nego.entity.Protocol;

import org.cloud.federation.model.Image;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;



	
	/**
	 *
	 * @author Rawia BOUABDELLAH - MIRACL
	 */
	public class ImageDAO implements Serializable {

	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		/**
		 * 
		 */
		//private static final long serialVersionUID = 1L;
		private EntityManagerFactory emf = null;
	    
	    
	    public ImageDAO() {
	        emf = Persistence.createEntityManagerFactory("JPA_PU");
	    }
	    

	    public EntityManager getEntityManager() {
	        return emf.createEntityManager();
	    }

	    public void create(Image image) throws PreexistingEntityException, Exception {
	        EntityManager em = null;
	        try {
	            em = getEntityManager();
	            em.getTransaction().begin();
	            em.persist(image);
	            em.getTransaction().commit();
	        } catch (Exception ex) {
	            if (findImage(image.getName()) != null) {
	                throw new PreexistingEntityException("Image " + image + " already exists.", ex);
	            }
	            throw ex;
	        } finally {
	            if (em != null) {
	                em.close();
	            }
	        }
	    }

	    public void edit(Image image) throws NonexistentEntityException, Exception {
	        EntityManager em = null;
	        try {
	            em = getEntityManager();
	            em.getTransaction().begin();
	            image = em.merge(image);
	            em.getTransaction().commit();
	        } catch (Exception ex) {
	            String msg = ex.getLocalizedMessage();
	            if (msg == null || msg.length() == 0) {
	                String id = image.getName();
	                if (findImage(id) == null) {
	                    throw new NonexistentEntityException("The image with id " + id + " no longer exists.");
	                }
	            }
	            throw ex;
	        } finally {
	            if (em != null) {
	                em.close();
	            }
	        }
	    }

	    public void destroy(String id) throws NonexistentEntityException {
	        EntityManager em = null;
	        try {
	            em = getEntityManager();
	            em.getTransaction().begin();
	            Image image;
	            try {
	                image = em.getReference(Image.class, id);
	                image.getName();
	            } catch (EntityNotFoundException enfe) {
	                throw new NonexistentEntityException("The Image with id " + id + " no longer exists.", enfe);
	            }
	            em.remove(image);
	            em.getTransaction().commit();
	        } finally {
	            if (em != null) {
	                em.close();
	            }
	        }
	    }

	    public List<Image> findImageEntities() {
	        return findImageEntities(true, -1, -1);
	    }

	    public List<Image> findImageEntities(int maxResults, int firstResult) {
	        return findImageEntities(false, maxResults, firstResult);
	    }

	    private List<Image> findImageEntities(boolean all, int maxResults, int firstResult) {
	        EntityManager em = getEntityManager();
	        try {
	        	System.out.println("start");
	        	Query q = em.createQuery("SELECT p FROM Image p"); 
	        	System.out.println("end");
	            if (!all) {
	                q.setMaxResults(maxResults);
	                q.setFirstResult(firstResult);
	            }
	            return q.getResultList();
	        } finally {
	            em.close();
	        }
	    }

	    public Image findImage(String id) {
	        EntityManager em = getEntityManager();
	        try {
	            return em.find(Image.class, id);
	        } finally {
	            em.close();
	        }
	    }

	    public int getImageCount() {
	    	EntityManager em = getEntityManager();
	        try {
	            return ((Long) em.createQuery("select count(p) from Image p").getSingleResult()).intValue();
	        } finally {
	            em.close();
	        }
	    }
	    /*
	    public List<Image> findByALL(String behavior, String partners, String rounds, String attributes)
	    {
	        EntityManager em=getEntityManager();
	        try{
	        return  em.createQuery("SELECT i FROM Images p WHERE p.behavior = ?1 and p.partnersNumber = ?2 and p.roundsNumbers = ?3 and p.attributesNumber = ?4").setParameter(1,behavior).setParameter(2,partners).setParameter(3,rounds).setParameter(4,attributes).getResultList();
	        }catch(Exception e)
	        {
	            e.printStackTrace();
	            return null;
	        }
	        finally{
	            em.close();
	        }
	    }
	   
	    public List<Image> findByBehavior(String behavior)
	    {
	        EntityManager em=getEntityManager();
	        try{
	        return  em.createQuery("SELECT p FROM Protocol p WHERE p.behavior = ?1").setParameter(1,behavior).getResultList();
	        }catch(Exception e)
	        {
	            e.printStackTrace();
	            return null;
	        }
	        finally{
	            em.close();
	        }
	    }
	    
	    public List<Image> findByBehaviorAndPartners(String behavior, String partners)
	    {
	        EntityManager em=getEntityManager();
	        try{
	        return  em.createQuery("SELECT p FROM Protocol p WHERE p.behavior = ?1 and p.partnersNumber = ?2").setParameter(1,behavior).setParameter(2,partners).getResultList();
	        }catch(Exception e)
	        {
	            e.printStackTrace();
	            return null;
	        }
	        finally{
	            em.close();
	        }
	    }
	    
	    public List<Image> findByBehaviorAndPartnersAndRounds(String behavior, String partners, String rounds)
	    {
	        EntityManager em=getEntityManager();
	        try{
	        return  em.createQuery("SELECT p FROM Protocol p WHERE p.behavior = ?1 and p.partnersNumber = ?2 and p.roundsNumbers = ?3").setParameter(1,behavior).setParameter(2,partners).setParameter(3,rounds).getResultList();
	        }catch(Exception e)
	        {
	            e.printStackTrace();
	            return null;
	        }
	        finally{
	            em.close();
	        }
	    }
	     */
	}
	    
	







