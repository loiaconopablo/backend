package ar.edu.unq.desapp.grupoA.repositories;

import ar.edu.unq.desapp.grupoA.models.UserModel;
import ar.edu.unq.desapp.grupoA.models.oauth.GoogleOauthCredential;
import ar.edu.unq.desapp.grupoA.repositories.utils.GenericRepository;
import ar.edu.unq.desapp.grupoA.repositories.utils.HibernateGenericDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


@Repository("googleOauthCredentialRepository")
public class GoogleOauthCredentialRepository extends HibernateGenericDAO<GoogleOauthCredential> implements GenericRepository<GoogleOauthCredential> {

    private static final long serialVersionUID = -4036535812105672112L;

    @Override
    protected Class<GoogleOauthCredential> getDomainClass() {
        return GoogleOauthCredential.class;
    }

    public GoogleOauthCredential findByUserId(String id) {
        Criteria cr = this.getSession().createCriteria(this.getDomainClass());

        cr.add(Restrictions.eq("googleUserId", id));
        return (GoogleOauthCredential) cr.uniqueResult();
    }
}
