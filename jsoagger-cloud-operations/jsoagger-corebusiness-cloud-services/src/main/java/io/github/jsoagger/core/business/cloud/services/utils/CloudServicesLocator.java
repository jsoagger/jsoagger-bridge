package io.github.jsoagger.core.business.cloud.services.utils;

import java.util.Properties;

import io.github.jsoagger.core.business.cloud.services.api.IAppVersionApi;
import io.github.jsoagger.core.business.cloud.services.api.IBatchApi;
import io.github.jsoagger.core.business.cloud.services.api.IBusinessRuleApi;
import io.github.jsoagger.core.business.cloud.services.api.ICategoryApi;
import io.github.jsoagger.core.business.cloud.services.api.IClientAuthenticationApi;
import io.github.jsoagger.core.business.cloud.services.api.IContactableApi;
import io.github.jsoagger.core.business.cloud.services.api.IContainedApi;
import io.github.jsoagger.core.business.cloud.services.api.IContainerApi;
import io.github.jsoagger.core.business.cloud.services.api.IContentFormatApi;
import io.github.jsoagger.core.business.cloud.services.api.IContentHolderApi;
import io.github.jsoagger.core.business.cloud.services.api.IDocumentApi;
import io.github.jsoagger.core.business.cloud.services.api.IEmailingApi;
import io.github.jsoagger.core.business.cloud.services.api.IFolderApi;
import io.github.jsoagger.core.business.cloud.services.api.IFolderTemplateApi;
import io.github.jsoagger.core.business.cloud.services.api.IGlobalAttributesApi;
import io.github.jsoagger.core.business.cloud.services.api.IHeartbeatApi;
import io.github.jsoagger.core.business.cloud.services.api.ILifecycleApi;
import io.github.jsoagger.core.business.cloud.services.api.ILifecycleManagedApi;
import io.github.jsoagger.core.business.cloud.services.api.IListvaluesApi;
import io.github.jsoagger.core.business.cloud.services.api.IMultiIdentificationApi;
import io.github.jsoagger.core.business.cloud.services.api.INotificationApi;
import io.github.jsoagger.core.business.cloud.services.api.IObjectCatalogApi;
import io.github.jsoagger.core.business.cloud.services.api.IObjectLinkApi;
import io.github.jsoagger.core.business.cloud.services.api.IOrganizationApi;
import io.github.jsoagger.core.business.cloud.services.api.IPartApi;
import io.github.jsoagger.core.business.cloud.services.api.IPeopleApi;
import io.github.jsoagger.core.business.cloud.services.api.IPersistableApi;
import io.github.jsoagger.core.business.cloud.services.api.IPreferenceAPI;
import io.github.jsoagger.core.business.cloud.services.api.IProductInstanceApi;
import io.github.jsoagger.core.business.cloud.services.api.IRCApi;
import io.github.jsoagger.core.business.cloud.services.api.IRoleApi;
import io.github.jsoagger.core.business.cloud.services.api.ISearchApi;
import io.github.jsoagger.core.business.cloud.services.api.IShoppingBasketApi;
import io.github.jsoagger.core.business.cloud.services.api.ITeamTemplateApi;
import io.github.jsoagger.core.business.cloud.services.api.IThumbedApi;
import io.github.jsoagger.core.business.cloud.services.api.ITypeApi;
import io.github.jsoagger.core.business.cloud.services.api.ITypeManagedApi;
import io.github.jsoagger.core.business.cloud.services.api.ITypedObjectLinkApi;
import io.github.jsoagger.core.business.cloud.services.api.IUserPrincipalApi;
import io.github.jsoagger.core.business.cloud.services.api.IViewDefinitionApi;
import io.github.jsoagger.core.business.cloud.services.impl.AppVersionApi;
import io.github.jsoagger.core.business.cloud.services.impl.AuthenticationApi;
import io.github.jsoagger.core.business.cloud.services.impl.BatchApi;
import io.github.jsoagger.core.business.cloud.services.impl.BusinessRuleApi;
import io.github.jsoagger.core.business.cloud.services.impl.CategoryApi;
import io.github.jsoagger.core.business.cloud.services.impl.ContactableApi;
import io.github.jsoagger.core.business.cloud.services.impl.ContainedApi;
import io.github.jsoagger.core.business.cloud.services.impl.ContainerApi;
import io.github.jsoagger.core.business.cloud.services.impl.ContentFormatApi;
import io.github.jsoagger.core.business.cloud.services.impl.ContentHolderApi;
import io.github.jsoagger.core.business.cloud.services.impl.DocumentApi;
import io.github.jsoagger.core.business.cloud.services.impl.EmailingApi;
import io.github.jsoagger.core.business.cloud.services.impl.FolderApi;
import io.github.jsoagger.core.business.cloud.services.impl.FolderTemplateApi;
import io.github.jsoagger.core.business.cloud.services.impl.GlobalAttributesApi;
import io.github.jsoagger.core.business.cloud.services.impl.HeartBeatApi;
import io.github.jsoagger.core.business.cloud.services.impl.LifecycleApi;
import io.github.jsoagger.core.business.cloud.services.impl.LifecycleManagedApi;
import io.github.jsoagger.core.business.cloud.services.impl.ListvaluesApi;
import io.github.jsoagger.core.business.cloud.services.impl.MultiIdentificationApi;
import io.github.jsoagger.core.business.cloud.services.impl.NotificationApi;
import io.github.jsoagger.core.business.cloud.services.impl.ObjectCatalogApi;
import io.github.jsoagger.core.business.cloud.services.impl.ObjectLinkApi;
import io.github.jsoagger.core.business.cloud.services.impl.OrganizationApi;
import io.github.jsoagger.core.business.cloud.services.impl.PartApi;
import io.github.jsoagger.core.business.cloud.services.impl.PeopleApi;
import io.github.jsoagger.core.business.cloud.services.impl.PersistableApi;
import io.github.jsoagger.core.business.cloud.services.impl.PreferenceApi;
import io.github.jsoagger.core.business.cloud.services.impl.ProductInstanceApi;
import io.github.jsoagger.core.business.cloud.services.impl.RCApi;
import io.github.jsoagger.core.business.cloud.services.impl.RoleApi;
import io.github.jsoagger.core.business.cloud.services.impl.SearchApi;
import io.github.jsoagger.core.business.cloud.services.impl.ShoppingBasketApi;
import io.github.jsoagger.core.business.cloud.services.impl.TeamTemplateApi;
import io.github.jsoagger.core.business.cloud.services.impl.ThumbedApi;
import io.github.jsoagger.core.business.cloud.services.impl.TypeApi;
import io.github.jsoagger.core.business.cloud.services.impl.TypeManagedApi;
import io.github.jsoagger.core.business.cloud.services.impl.TypedObjectLinkApi;
import io.github.jsoagger.core.business.cloud.services.impl.UserPrincipalApi;
import io.github.jsoagger.core.business.cloud.services.impl.ViewDefinitionApi;
import io.github.jsoagger.core.ioc.api.annotations.Bean;
import io.github.jsoagger.core.ioc.api.annotations.BeansProvider;
import io.github.jsoagger.core.ioc.api.annotations.Named;

/**
 * Gives access to remote services . This service should be injected to client
 * API.
 *
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
@BeansProvider
public class CloudServicesLocator {

  public static IClientAuthenticationApi	authenticationApi;
  public static IHeartbeatApi				heartbeatApi;
  public static IUserPrincipalApi			userPrincipalApi;
  public static IContainedApi               containedApi;
  public static IContainerApi				containerApi;
  public static IBatchApi					batchApi;
  public static IListvaluesApi			listvaluesApi;
  public static IPersistableApi			persistableApi;
  public static ITypeApi					typeApi;
  public static ITypedObjectLinkApi		typedObjectLinkApi;
  public static IObjectLinkApi			objectLinkApi;
  public static IGlobalAttributesApi		globalAttributesApi;
  public static ILifecycleApi				lifecycleApi;
  public static IRCApi					rcApi;
  public static ILifecycleManagedApi		lifecycleManagedApi;
  public static IContentHolderApi			contentHolderApi;
  public static ITeamTemplateApi			teamTemplateApi;
  public static IFolderApi				folderApi;
  public static IPartApi					partApi;
  public static ISearchApi				searchApi;
  public static ITypeManagedApi			typeManagedApi;
  public static IBusinessRuleApi			businessRuleApi;
  public static INotificationApi			notificationApi;
  public static IThumbedApi				thumbedApi;
  public static IContentFormatApi			contentFormatApi;
  public static IDocumentApi documentApi;
  public static IPeopleApi peopleApi;
  public static IPreferenceAPI preferenceApi;
  public static ICategoryApi categoryApi;
  public static IOrganizationApi organizationApi;
  public static IContactableApi contactableApi;
  public static IProductInstanceApi productInstanceApi;
  public static IShoppingBasketApi shoppingBasketApi;
  public static IMultiIdentificationApi multiIdentificationApi;
  public static IFolderTemplateApi folderTemplateApi;
  public static IRoleApi roleApi;
  public static IObjectCatalogApi objectCatalogApi;
  public static IAppVersionApi appVersionApi;
  public static IEmailingApi emailingApi;
  public static IViewDefinitionApi viewDefinitionApi;

  /**
   * Constructor
   */
  public CloudServicesLocator() {
  }

  /**
   * If need initialization
   */
  public void init() {
  }

  public void setViewDefinitionApi(IViewDefinitionApi viewDefinitionApi) {
    CloudServicesLocator.viewDefinitionApi = viewDefinitionApi;
  }


  public void setEmailingApi(IEmailingApi emailingApi) {
    CloudServicesLocator.emailingApi = emailingApi;
  }

  public void setAppVersionApi(IAppVersionApi appVersionApi) {
    CloudServicesLocator.appVersionApi = appVersionApi;
  }

  public void setObjectCatalogApi(IObjectCatalogApi objectCatalogApi) {
    CloudServicesLocator.objectCatalogApi = objectCatalogApi;
  }

  public void setRoleApi(IRoleApi roleApi) {
    CloudServicesLocator.roleApi = roleApi;
  }

  public void setFolderTemplateApi(IFolderTemplateApi folderTemplateApi) {
    CloudServicesLocator.folderTemplateApi = folderTemplateApi;
  }

  public void setMultiIdentificationApi(IMultiIdentificationApi multiIdentificationApi) {
    CloudServicesLocator.multiIdentificationApi = multiIdentificationApi;
  }

  public void setShoppingBasketApi(IShoppingBasketApi shoppingBasketApi) {
    CloudServicesLocator.shoppingBasketApi = shoppingBasketApi;
  }

  public void setProductInstanceApi(IProductInstanceApi productInstanceApi) {
    CloudServicesLocator.productInstanceApi = productInstanceApi;
  }

  public void setContactableApi(IContactableApi contactableApi) {
    CloudServicesLocator.contactableApi = contactableApi;
  }

  public void setOrganizationApi(IOrganizationApi organizationApi) {
    CloudServicesLocator.organizationApi = organizationApi;
  }

  public void setCategoryApi(ICategoryApi categoryApi) {
    CloudServicesLocator.categoryApi = categoryApi;
  }

  public void setPreferenceApi(IPreferenceAPI preferenceApi) {
    CloudServicesLocator.preferenceApi = preferenceApi;
  }

  public void setPeopleApi(IPeopleApi peopleApi) {
    CloudServicesLocator.peopleApi = peopleApi;
  }

  public void setDocumentApi(IDocumentApi documentApi) {
    CloudServicesLocator.documentApi = documentApi;
  }

  public void setContentFormatApi(IContentFormatApi contentFormatApi) {
    CloudServicesLocator.contentFormatApi = contentFormatApi;
  }

  public void setThumbedApi(IThumbedApi thumbedApi) {
    CloudServicesLocator.thumbedApi = thumbedApi;
  }

  public void setNotificationApi(INotificationApi notificationApi) {
    CloudServicesLocator.notificationApi = notificationApi;
  }

  public void setTypeManagedApi(ITypeManagedApi typeManagedApi) {
    CloudServicesLocator.typeManagedApi = typeManagedApi;
  }

  public void setSearchApi(ISearchApi searchApi) {
    CloudServicesLocator.searchApi = searchApi;
  }


  public void setBusinessRuleApi(IBusinessRuleApi businessRuleApi) {
    CloudServicesLocator.businessRuleApi = businessRuleApi;
  }

  public void setPartApi(IPartApi partApi) {
    CloudServicesLocator.partApi = partApi;
  }


  public void setFolderedApi(IFolderApi folderApi) {
    CloudServicesLocator.folderApi = folderApi;
  }


  public void setTeamTemplateApi(ITeamTemplateApi teamTemplateApi) {
    CloudServicesLocator.teamTemplateApi = teamTemplateApi;
  }



  public void setContentHolderApi(IContentHolderApi contentHolderApi) {
    CloudServicesLocator.contentHolderApi = contentHolderApi;
  }



  public void setGlobalAttributesApi(IGlobalAttributesApi globalAttributesApi) {
    CloudServicesLocator.globalAttributesApi = globalAttributesApi;
  }



  public void setLifecycleManagedApi(ILifecycleManagedApi lifecycleManagedApi) {
    CloudServicesLocator.lifecycleManagedApi = lifecycleManagedApi;
  }



  public void setRcApi(IRCApi rcApi) {
    CloudServicesLocator.rcApi = rcApi;
  }



  public void setLifecycleApi(ILifecycleApi lifecycleApi) {
    CloudServicesLocator.lifecycleApi = lifecycleApi;
  }



  public void setObjectLinkApi(IObjectLinkApi objectLinkApi) {
    CloudServicesLocator.objectLinkApi = objectLinkApi;
  }



  public void setTypedObjectLinkApi(ITypedObjectLinkApi typedObjectLinkApi) {
    CloudServicesLocator.typedObjectLinkApi = typedObjectLinkApi;
  }


  /**
   * Setter of typeApi
   *
   * @param typeApi
   *            the typeApi to set
   */

  public void setTypeApi(ITypeApi typeApi) {
    CloudServicesLocator.typeApi = typeApi;
  }


  /**
   * Setter of persistableApi
   *
   * @param persistableApi
   *            the persistableApi to set
   */

  public void setPersistableApi(IPersistableApi persistableApi) {
    CloudServicesLocator.persistableApi = persistableApi;
  }


  /**
   * Setter of listvaluesApi
   *
   * @param listvaluesApi
   *            the listvaluesApi to set
   */

  public void setListvaluesApi(IListvaluesApi listvaluesApi) {
    CloudServicesLocator.listvaluesApi = listvaluesApi;
  }


  /**
   * Setter of authenticationApi
   *
   * @param authenticationApi
   *            the authenticationApi to set
   */

  public void setAuthenticationApi(IClientAuthenticationApi authenticationApi) {
    CloudServicesLocator.authenticationApi = authenticationApi;
  }


  /**
   * Setter of heartbeatApi
   *
   * @param heartbeatApi
   *            the heartbeatApi to set
   */

  public void setHeartbeatApi(IHeartbeatApi heartbeatApi) {
    CloudServicesLocator.heartbeatApi = heartbeatApi;
  }


  /**
   * Setter of userPrincipalApi
   *
   * @param userPrincipalApi
   *            the userPrincipalApi to set
   */

  public void setUserPrincipalApi(IUserPrincipalApi userPrincipalApi) {
    CloudServicesLocator.userPrincipalApi = userPrincipalApi;
  }


  /**
   * Setter of containerService
   *
   * @param containerApi
   *            the containerService to set
   */

  public void setContainerApi(IContainerApi containerApi) {
    CloudServicesLocator.containerApi = containerApi;
  }


  /**
   * @param containedApi
   */
  public void setContainedApi(IContainedApi containedApi) {
    CloudServicesLocator.containedApi = containedApi;
  }


  /**
   * Setter of batchApi
   *
   * @param batchApi
   *            the batchApi to set
   */

  public void setBatchApi(IBatchApi batchApi) {
    CloudServicesLocator.batchApi = batchApi;
  }


  /**
   * @return the viewDefinitionApi
   */
  @Bean
  @Named("ViewDefinitionApi")
  public static IViewDefinitionApi getViewDefinitionApi() {
    if(viewDefinitionApi == null) {
      viewDefinitionApi = new ViewDefinitionApi();
      ((ViewDefinitionApi)viewDefinitionApi).setCloudServicesProperties(cloudServicesProperties());
    }

    return viewDefinitionApi;
  }


  /**
   * @return the emailApi
   */
  @Bean
  @Named("EmailingApi")
  public static IEmailingApi getEmailingApi() {
    if(emailingApi == null) {
      emailingApi = new EmailingApi();
      ((EmailingApi)emailingApi).setCloudServicesProperties(cloudServicesProperties());
    }

    return emailingApi;
  }


  /**
   * @return the appVersionApi
   */
  @Bean
  @Named("AppVersionApi")
  public static IAppVersionApi getAppVersionApi() {
    if(appVersionApi == null) {
      appVersionApi = new AppVersionApi();
      ((AppVersionApi)appVersionApi).setCloudServicesProperties(cloudServicesProperties());
    }

    return appVersionApi;
  }


  /**
   * @return the objectCatalogApi
   */
  @Bean
  @Named("ObjectCatalogApi")
  public static IObjectCatalogApi getObjectCatalogApi() {
    if(objectCatalogApi == null) {
      objectCatalogApi = new ObjectCatalogApi();
      ((ObjectCatalogApi)objectCatalogApi).setCloudServicesProperties(cloudServicesProperties());
    }

    return objectCatalogApi;
  }

  /**
   * @return the authenticationApi
   */
  @Bean
  @Named("RoleApi")
  public static IRoleApi getRoleApi() {
    if(roleApi == null) {
      roleApi = new RoleApi();
      ((RoleApi)roleApi).setCloudServicesProperties(cloudServicesProperties());
    }
    return roleApi;
  }


  /**
   * @return the authenticationApi
   */
  @Bean
  @Named("MultiIdentificationApi")
  public static IMultiIdentificationApi getMultiIdentificationApi() {
    if(multiIdentificationApi == null) {
      multiIdentificationApi = new MultiIdentificationApi();
      ((ShoppingBasketApi)multiIdentificationApi).setCloudServicesProperties(cloudServicesProperties());
    }
    return multiIdentificationApi;
  }


  /**
   * @return the authenticationApi
   */
  @Bean
  @Named("ShoppingBasketApi")
  public static IShoppingBasketApi getShoppingBasketApi() {
    if(shoppingBasketApi == null) {
      shoppingBasketApi = new ShoppingBasketApi();
      ((ShoppingBasketApi)shoppingBasketApi).setCloudServicesProperties(cloudServicesProperties());
    }
    return shoppingBasketApi;
  }

  /**
   * @return the ProductInstanceApi
   */
  @Bean
  @Named("ProductInstanceApi")
  public static IProductInstanceApi getProductInstanceApi() {
    if(productInstanceApi == null) {
      productInstanceApi = new ProductInstanceApi();
      ((ProductInstanceApi)productInstanceApi).setCloudServicesProperties(cloudServicesProperties());
    }
    return productInstanceApi;
  }

  /**
   * @return the authenticationApi
   */
  @Bean
  @Named("ContactableApi")
  public static IContactableApi getContactableApi() {
    if(contactableApi == null) {
      contactableApi = new ContactableApi();
      ((ContactableApi)contactableApi).setCloudServicesProperties(cloudServicesProperties());
    }
    return contactableApi;
  }

  /**
   * @return the authenticationApi
   */
  @Bean
  @Named("CategoryApi")
  public static IOrganizationApi getOrganizationApi() {
    if(organizationApi == null) {
      organizationApi = new OrganizationApi();
      ((OrganizationApi)organizationApi).setCloudServicesProperties(cloudServicesProperties());
    }
    return organizationApi;
  }

  /**
   * @return the authenticationApi
   */
  @Bean
  @Named("CategoryApi")
  public static ICategoryApi getCategoryApi() {
    if(categoryApi == null) {
      categoryApi = new CategoryApi();
      ((CategoryApi)categoryApi).setCloudServicesProperties(cloudServicesProperties());
    }
    return categoryApi;
  }

  /**
   * @return the authenticationApi
   */
  @Bean
  @Named("AuthenticationApi")
  public static IClientAuthenticationApi getAuthenticationApi() {
    if(authenticationApi == null) {
      authenticationApi = new AuthenticationApi();
      ((AuthenticationApi)authenticationApi).setCloudServicesProperties(cloudServicesProperties());
    }
    return authenticationApi;
  }

  /**
   * @return the heartbeatApi
   */
  @Bean
  @Named("HeartbeatApi")
  public static IHeartbeatApi getHeartbeatApi() {
    if(heartbeatApi == null) {
      heartbeatApi  = new HeartBeatApi();
      ((HeartBeatApi)authenticationApi).setCloudServicesProperties(cloudServicesProperties());
    }
    return heartbeatApi;
  }

  /**
   * @return the userPrincipalApi
   */
  @Bean
  @Named("UserPrincipalApi")
  public static IUserPrincipalApi getUserPrincipalApi() {
    if(userPrincipalApi == null) {
      userPrincipalApi = new UserPrincipalApi();
      ((UserPrincipalApi)userPrincipalApi).setCloudServicesProperties(cloudServicesProperties());
    }
    return userPrincipalApi;
  }

  /**
   * @return the containerApi
   */
  @Bean
  @Named("ContainerApi")
  public static IContainerApi getContainerApi() {
    if(containerApi == null) {
      containerApi = new ContainerApi();
      ((ContainerApi)containerApi).setCloudServicesProperties(cloudServicesProperties());
    }
    return containerApi;
  }

  /**
   * @return the containedApi
   */
  @Bean
  @Named("ContainedApi")
  public static IContainedApi getContainedApi() {
    if(containedApi == null) {
      containedApi = new ContainedApi();
      ((ContainedApi)containedApi).setCloudServicesProperties(cloudServicesProperties());
    }
    return containedApi;
  }

  /**
   * @return the batchApi
   */
  @Bean
  @Named("BatchApi")
  public static IBatchApi getBatchApi() {
    if(batchApi == null) {
      batchApi= new BatchApi();
      ((BatchApi)batchApi).setCloudServicesProperties(cloudServicesProperties());
    }
    return batchApi;
  }

  /**
   * @return the listvaluesApi
   */
  @Bean
  @Named("ListvaluesApi")
  public static IListvaluesApi getListvaluesApi() {
    if(listvaluesApi == null) {
      listvaluesApi = new ListvaluesApi();
      ((ListvaluesApi)listvaluesApi).setCloudServicesProperties(cloudServicesProperties());
    }
    return listvaluesApi;
  }

  /**
   * @return the persistableApi
   */
  @Bean
  @Named("PersistableApi")
  public static IPersistableApi getPersistableApi() {
    if(persistableApi == null) {
      persistableApi = new PersistableApi();
      ((PersistableApi)persistableApi).setCloudServicesProperties(cloudServicesProperties());
    }
    return persistableApi;
  }

  /**
   * @return the typeApi
   */
  @Bean
  @Named("TypeApi")
  public static ITypeApi getTypeApi() {
    if(typeApi == null) {
      typeApi = new TypeApi();
      ((TypeApi)typeApi).setCloudServicesProperties(cloudServicesProperties());
    }
    return typeApi;
  }

  /**
   * @return the typedObjectLinkApi
   */
  @Bean
  @Named("TypedObjectLinkApi")
  public static ITypedObjectLinkApi getTypedObjectLinkApi() {
    if(typedObjectLinkApi == null) {
      typedObjectLinkApi = new TypedObjectLinkApi();
      ((TypedObjectLinkApi)typedObjectLinkApi).setCloudServicesProperties(cloudServicesProperties());
    }
    return typedObjectLinkApi;
  }

  /**
   * @return the objectLinkApi
   */
  @Bean
  @Named("ObjectLinkApi")
  public static IObjectLinkApi getObjectLinkApi() {
    if(objectLinkApi == null) {
      objectLinkApi = new ObjectLinkApi();
      ((ObjectLinkApi)objectLinkApi).setCloudServicesProperties(cloudServicesProperties());
    }
    return objectLinkApi;
  }

  /**
   * @return the globalAttributesApi
   */
  @Bean
  @Named("GlobalAttributesApi")
  public static IGlobalAttributesApi getGlobalAttributesApi() {
    if(globalAttributesApi == null) {
      globalAttributesApi = new GlobalAttributesApi();
      ((GlobalAttributesApi)globalAttributesApi).setCloudServicesProperties(cloudServicesProperties());
    }
    return globalAttributesApi;
  }

  /**
   * @return the globalAttributesApi
   */
  @Bean
  @Named("PreferenceAPI")
  public static IPreferenceAPI getPreferenceAPI() {
    if(preferenceApi == null) {
      preferenceApi = new PreferenceApi();
      ((PreferenceApi)preferenceApi).setCloudServicesProperties(cloudServicesProperties());
    }
    return preferenceApi;
  }


  /**
   * @return the lifecycleApi
   */
  @Bean
  @Named("LifecycleApi")
  public static ILifecycleApi getLifecycleApi() {
    if(lifecycleApi == null) {
      lifecycleApi = new LifecycleApi();
      ((LifecycleApi)authenticationApi).setCloudServicesProperties(cloudServicesProperties());
    }
    return lifecycleApi;
  }

  /**
   * @return the rcApi
   */
  @Bean
  @Named("RCApi")
  public static IRCApi getRcApi() {
    if(rcApi == null) {
      rcApi= new RCApi();
      ((RCApi)authenticationApi).setCloudServicesProperties(cloudServicesProperties());
    }
    return rcApi;
  }

  /**
   * @return the lifecycleManagedApi
   */
  @Bean
  @Named("LifecycleManagedApi")
  public static ILifecycleManagedApi getLifecycleManagedApi() {
    if(lifecycleManagedApi == null) {
      lifecycleManagedApi = new LifecycleManagedApi();
      ((LifecycleManagedApi)lifecycleManagedApi).setCloudServicesProperties(cloudServicesProperties());
    }
    return lifecycleManagedApi;
  }

  /**
   * @return the contentHolderApi
   */
  @Bean
  @Named("ContentHolderApi")
  public static IContentHolderApi getContentHolderApi() {
    if(contentHolderApi == null) {
      contentHolderApi = new ContentHolderApi();
      ((ContentHolderApi)contentHolderApi).setCloudServicesProperties(cloudServicesProperties());
    }
    return contentHolderApi;
  }

  /**
   * @return the teamTemplateApi
   */
  @Bean
  @Named("TeamTemplateApi")
  public static ITeamTemplateApi getTeamTemplateApi() {
    if(teamTemplateApi == null) {
      teamTemplateApi = new TeamTemplateApi();
      ((TeamTemplateApi)teamTemplateApi).setCloudServicesProperties(cloudServicesProperties());
    }
    return teamTemplateApi;
  }

  /**
   * @return the folderApi
   */
  @Bean
  @Named("FolderApi")
  public static IFolderApi getFolderApi() {
    if(folderApi == null) {
      folderApi = new FolderApi();
      ((FolderApi)folderApi).setCloudServicesProperties(cloudServicesProperties());
    }
    return folderApi;
  }

  /**
   * @return the partApi
   */
  @Bean
  @Named("PartApi")
  public static IPartApi getPartApi() {
    if(partApi == null) {
      partApi = new PartApi();
      ((PartApi)partApi).setCloudServicesProperties(cloudServicesProperties());
    }
    return partApi;
  }

  /**
   * @return the searchApi
   */
  @Bean
  @Named("SearchApi")
  public static ISearchApi getSearchApi() {
    if(searchApi == null) {
      searchApi = new SearchApi();
      ((SearchApi)searchApi).setCloudServicesProperties(cloudServicesProperties());
    }
    return searchApi;
  }

  /**
   * @return the typeManagedApi
   */
  @Bean
  @Named("TypeManagedApi")
  public static ITypeManagedApi getTypeManagedApi() {
    if(typeManagedApi == null) {
      typeManagedApi =  new TypeManagedApi();
      ((TypeManagedApi)typeManagedApi).setCloudServicesProperties(cloudServicesProperties());
    }
    return typeManagedApi;
  }

  /**
   * @return the businessRuleApi
   */
  @Bean
  @Named("BusinessRuleApi")
  public static IBusinessRuleApi getBusinessRuleApi() {
    if(businessRuleApi == null) {
      businessRuleApi = new BusinessRuleApi();
      ((BusinessRuleApi)businessRuleApi).setCloudServicesProperties(cloudServicesProperties());
    }
    return businessRuleApi;
  }

  /**
   * @return the notificationApi
   */
  @Bean
  @Named("NotificationApi")
  public static INotificationApi getNotificationApi() {
    if(notificationApi == null) {
      notificationApi = new NotificationApi();
      ((NotificationApi)notificationApi).setCloudServicesProperties(cloudServicesProperties());
    }
    return notificationApi;
  }

  /**
   * @return the thumbedApi
   */
  @Bean
  @Named("ThumbedApi")
  public static IThumbedApi getThumbedApi() {
    if(thumbedApi == null) {
      thumbedApi =  new ThumbedApi();
      ((ThumbedApi)thumbedApi).setCloudServicesProperties(cloudServicesProperties());
    }
    return thumbedApi;
  }

  /**
   * @return the contentFormatApi
   */
  @Bean
  @Named("ContentFormatApi")
  public static IContentFormatApi getContentFormatApi() {
    if(contentFormatApi == null) {
      contentFormatApi = new ContentFormatApi();
      ((ContentFormatApi)contentFormatApi).setCloudServicesProperties(cloudServicesProperties());
    }
    return contentFormatApi;
  }

  /**
   * @return the FolderTemplateApi
   */
  @Bean
  @Named("FolderTemplateApi")
  public static IFolderTemplateApi getFolderTemplateApi() {
    if(folderTemplateApi == null) {
      folderTemplateApi = new FolderTemplateApi();
      ((FolderTemplateApi)folderTemplateApi).setCloudServicesProperties(cloudServicesProperties());
    }
    return folderTemplateApi;
  }


  /**
   * @return the documentApi
   */
  @Bean
  @Named("DocumentApi")
  public static IDocumentApi getDocumentApi() {
    if(documentApi == null) {
      documentApi = new DocumentApi();
      ((DocumentApi)documentApi).setCloudServicesProperties(cloudServicesProperties());
    }
    return documentApi;
  }

  /**
   * @return the documentApi
   */
  @Bean
  @Named("PeopleApi")
  public static IPeopleApi getPeopleApi() {
    if(peopleApi == null) {
      peopleApi = new PeopleApi();
      ((PeopleApi)peopleApi).setCloudServicesProperties(cloudServicesProperties());
    }
    return peopleApi;
  }

  /**
   * @return
   */
  @Bean
  @Named("cloudServicesProperties")
  public static Properties cloudServicesProperties() {
    Properties properties = new Properties();
    properties.put("remoteServerLocation", "http://localhost:8080/jsoagger-corebusiness-services/serv/core");
    properties.put("authenticationApi", "/api/authentication");
    return properties;
  }

}
