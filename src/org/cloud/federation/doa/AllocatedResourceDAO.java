package org.cloud.federation.doa;



import org.cloud.federation.dao.exceptions.NonexistentEntityException;
import org.cloud.federation.dao.exceptions.PreexistingEntityException;
//import com.miracl.nego.entity.Protocol;

import org.cloud.federation.model.AllocatedResource;
import org.cloud.federation.model.Image;
import org.cloud.federation.model.Resource;

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
	public class AllocatedResourceDAO implements Serializable {

	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		/**
		 * 
		 */
		//private static final long serialVersionUID = 1L;
		private EntityManagerFactory emf = null;
	    
	    
	    public AllocatedResourceDAO() {
	        emf = Persistence.createEntityManagerFactory("JPA_PU");
	    }
	    

	    public EntityManager getEntityManager() {
	        return emf.createEntityManager();
	    }

	    public void create(AllocatedResource vm) throws PreexistingEntityException, Exception {
	        EntityManager em = null;
	        try {
	            em = getEntityManager();
	            em.getTransaction().begin();
	            em.persist(vm);
	            em.getTransaction().commit();
	        } catch (Exception ex) {
	            if (findVM(vm.getId()) != null) {
	                throw new PreexistingEntityException("Virtal Machine " + vm + " already exists.", ex);
	            }
	            throw ex;
	        } finally {
	            if (em != null) {
	                em.close();
	            }
	        }
	    }

	    public void edit(AllocatedResource vm) throws NonexistentEntityException, Exception {
	        EntityManager em = null;
	        try {
	            em = getEntityManager();
	            em.getTransaction().begin();
	            vm = em.merge(vm);
	            em.getTransaction().commit();
	        } catch (Exception ex) {
	            String msg = ex.getLocalizedMessage();
	            if (msg == null || msg.length() == 0) {
	                int id = vm.getId();
	                if (findVM(id) == null) {
	                    throw new NonexistentEntityException("The Virtal Machine with id " + id + " no longer exists.");
	                }
	            }
	            throw ex;
	        } finally {
	            if (em != null) {
	                em.close();
	            }
	        }
	    }
/*
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
*/
	    public List<AllocatedResource> findVMEntities() {
	        return findVMEntities(true, -1, -1);
	    }

	    public List<AllocatedResource> findVMEntities(int maxResults, int firstResult) {
	        return findVMEntities(false, maxResults, firstResult);
	    }

	    private List<AllocatedResource> findVMEntities(boolean all, int maxResults, int firstResult) {
	        EntityManager em = getEntityManager();
	        try {
	        	System.out.println("start");
	        	Query q = em.createQuery("SELECT v FROM AllocatedResource v"); 
	        	System.out.println("end");
	            if (!all) {
	                q.setMaxResults(maxResults);
	                q.setFirstResult(firstResult);
	            }
	            return q.getResultList();
	        } 
	        
	        finally {
	            em.close();
	        }
	    }

	    public AllocatedResource findVM(int id) {
	        EntityManager em = getEntityManager();
	        try {
	            return em.find(AllocatedResource.class, id);
	        } finally {
	            em.close();
	        }
	    }

	    public int getVirtualMachineCount() {
	    	EntityManager em = getEntityManager();
	        try {
	            return ((Long) em.createQuery("select count(v) from AllocatedResource v").getSingleResult()).intValue();
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
	    
	







