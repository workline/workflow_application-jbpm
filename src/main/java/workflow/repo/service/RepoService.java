package workflow.repo.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import loggee.api.Logged;
import vrds.model.RepoAttributeDefinition;
import vrds.model.RepoAttributeDefinition_;
import vrds.model.RepoDefinition;
import vrds.model.RepoDefinition_;
import vrds.model.RepoItem;
import workflow.util.Primary;

@Stateless
@Logged
public class RepoService {
    @Inject
    @Primary
    private EntityManager entityManager;

    public RepoItem getRepo(Long repoId) {
        RepoItem repo;

        repo = entityManager.find(RepoItem.class, repoId);

        return repo;
    }

    public Object getRepoAttributeValue(Long repoId, String repoAttributeName) {
        Object repoAttributeValue;

        RepoItem repo = getRepo(repoId);
        repoAttributeValue = repo.getValue(repoAttributeName);

        return repoAttributeValue;
    }

    public RepoDefinition getRepoDefinition(String name) {
        RepoDefinition repoDefinition;

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<RepoDefinition> repoDefinitionQuery = criteriaBuilder.createQuery(RepoDefinition.class);

        Root<RepoDefinition> repoDefinitionRoot = repoDefinitionQuery.from(RepoDefinition.class);

        repoDefinitionQuery.select(repoDefinitionRoot).where(criteriaBuilder.equal(repoDefinitionRoot.get(RepoDefinition_.name), name));

        TypedQuery<RepoDefinition> repoDefinitionTypedQuery = entityManager.createQuery(repoDefinitionQuery);
        repoDefinition = repoDefinitionTypedQuery.getSingleResult();

        return repoDefinition;
    }

    public RepoAttributeDefinition getRepoAttributeDefinition(String name) {
        RepoAttributeDefinition repoAttributeDefinition;

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<RepoAttributeDefinition> repoAttributeDefinitionQuery = criteriaBuilder.createQuery(RepoAttributeDefinition.class);

        Root<RepoAttributeDefinition> repoAttributeDefinitionRoot = repoAttributeDefinitionQuery.from(RepoAttributeDefinition.class);

        repoAttributeDefinitionQuery.select(repoAttributeDefinitionRoot).where(
                criteriaBuilder.equal(repoAttributeDefinitionRoot.get(RepoAttributeDefinition_.name), name));

        TypedQuery<RepoAttributeDefinition> repoDefinitionTypedQuery = entityManager.createQuery(repoAttributeDefinitionQuery);
        repoAttributeDefinition = repoDefinitionTypedQuery.getSingleResult();

        return repoAttributeDefinition;
    }
}
