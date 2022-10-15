package org.cloud.federation.doa;


import org.cloud.federation.dao.exceptions.NonexistentEntityException;
import org.cloud.federation.dao.exceptions.PreexistingEntityException;
import org.cloud.federation.model.User;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;



	
	/**
	 *
	 * @author Rawia BOUABDELLAH - SOIE
	 */
	public class UserDAO implements Serializable {

	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private EntityManagerFactory emf = null;
	    
	    
	    public UserDAO() {
	        emf = Persistence.createEntityManagerFactory("JPA_PU");
	    }
	    

	    public EntityManager getEntityManager() {
	        return emf.createEntityManager();
	    }

	    public void create(User user) throws PreexistingEntityException, Exception {
	        EntityManager em = null;
	        try {
	            em = getEntityManager();
	            em.getTransaction().begin();
	            em.persist(user);
	            em.getTransaction().commit();
	        } catch (Exception ex) {
	            if (findUser(user.getLogin()) != null) {
	                throw new PreexistingEntityException("User " + user + " already exists.", ex);
	            }
	            throw ex;
	        } finally {
	            if (em != null) {
	                em.close();
	            }
	        }
	    }

	    public void edit(User user) throws NonexistentEntityException, Exception {
	        EntityManager em = null;
	        try {
	            em = getEntityManager();
	            em.getTransaction().begin();
	            user = em.merge(user);
	            em.getTransaction().commit();
	        } catch (Exception ex) {
	            String msg = ex.getLocalizedMessage();
	            if (msg == null || msg.length() == 0) {
	                String id = user.getLogin();
	                if (findUser(id) == null) {
	                    throw new NonexistentEntityException("The user with id " + id + " no longer exists.");
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
	            User user;
	            try {
	                user = em.getReference(User.class, id);
	                user.getLogin();
	            } catch (EntityNotFoundException enfe) {
	                throw new NonexistentEntityException("The user with id " + id + " no longer exists.", enfe);
	            }
	            em.remove(user);
	            em.getTransaction().commit();
	        } finally {
	            if (em != null) {
	                em.close();
	            }
	        }
	    }

	    public List<User> findUserEntities() {
	        return findUserEntities(true, -1, -1);
	    }

	    public List<User> findUserEntities(int maxResults, int firstResult) {
	        return findUserEntities(false, maxResults, firstResult);
	    }

	    private List<User> findUserEntities(boolean all, int maxResults, int firstResult) {
	        EntityManager em = getEntityManager();
	        try {
	        	Query q = em.createQuery("SELECT u FROM User u");
	            if (!all) {
	                q.setMaxResults(maxResults);
	                q.setFirstResult(firstResult);
	            }
	            return q.getResultList();
	        } finally {
	            em.close();
	        }
	    }

	    public User findUser(String id) {
	        EntityManager em = getEntityManager();
	        try {
	            return em.find(User.class, id);
	        } finally {
	            em.close();
	        }
	    }

	    public int getUserCount() {
	    	EntityManager em = getEntityManager();
	        try {
	            return ((Long) em.createQuery("select count(u) from User u").getSingleResult()).intValue();
	        } finally {
	            em.close();
	        }
	    }
	    /*
	    public List<User> findByALL(String behavior, String partners, String rounds, String attributes)
	    {
	        EntityManager em=getEntityManager();
	        try{
	        return  em.createQuery("SELECT p FROM Protocol p WHERE p.behavior = ?1 and p.partnersNumber = ?2 and p.roundsNumbers = ?3 and p.attributesNumber = ?4").setParameter(1,behavior).setParameter(2,partners).setParameter(3,rounds).setParameter(4,attributes).getResultList();
	        }catch(Exception e)
	        {
	            e.printStackTrace();
	            return null;
	        }
	        finally{
	            em.close();
	        }
	    }
	    
	    public List<User> findByBehavior(String behavior)
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
	    
	    public List<User> findByBehaviorAndPartners(String behavior, String partners)
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
	    
	    public List<User> findByBehaviorAndPartnersAndRounds(String behavior, String partners, String rounds)
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
	    
	







