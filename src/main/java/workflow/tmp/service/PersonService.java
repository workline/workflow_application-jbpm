package workflow.tmp.service;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import loggee.api.Logged;
import vrds.model.RepoAttributeDefinition;
import vrds.model.RepoDefinition;
import vrds.model.RepoItem;
import vrds.model.RepoItemAttribute;
import workflow.repo.service.RepoService;
import workflow.util.Primary;

@Stateless
@Logged
public class PersonService {
    @Inject
    @Primary
    private EntityManager entityManager;
    @Inject
    private RepoService repoService;

    // TODO Temporary method, too specific
    public Long createRandomPerson(String name) {

        RepoDefinition personRepoDefinition = repoService.getRepoDefinition("ProcessData");
        RepoAttributeDefinition firstNameRepoAttributeDefinition = repoService.getRepoAttributeDefinition("First name");
        RepoAttributeDefinition motherRepoAttributeDefinition = repoService.getRepoAttributeDefinition("Mother");

        RepoItem repo = new RepoItem();
        repo.setDefinition(personRepoDefinition);

        RepoItemAttribute firstNameRepoAttribute = new RepoItemAttribute();
        firstNameRepoAttribute.setDefinition(firstNameRepoAttributeDefinition);
        firstNameRepoAttribute.setValue(name);

        RepoItemAttribute motherRepoAttribute = new RepoItemAttribute();
        motherRepoAttribute.setDefinition(motherRepoAttributeDefinition);
        RepoItem mom = null;
        try {
            mom = repoService.getRepo(2L);
            mom = repoService.getRepo(1L);
        } catch (Exception e) {
        }
        motherRepoAttribute.setValue(mom);

        Set<RepoItemAttribute> repoAttributes = new HashSet<RepoItemAttribute>();

        repoAttributes.add(firstNameRepoAttribute);
        repoAttributes.add(motherRepoAttribute);

        repo.setRepoItemAttributes(repoAttributes);

        entityManager.persist(repo);

        return repo.getId();
    }
}
