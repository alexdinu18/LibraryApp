package views;

import entity.Book;
import entity.Cart;
import entity.LibraryUser;
import views.util.JsfUtil;
import views.util.PaginationHelper;
import models.LibraryUserFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;


@Named("libraryUserController")
@SessionScoped
public class LibraryUserController implements Serializable {


    private LibraryUser current;
    private DataModel items = null;
    @EJB private models.LibraryUserFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private Book bookParameter;
    private Logger logger = Logger.getLogger("libraryUserController");

    public LibraryUserController() {
    }

    public Book getBookParameter() {
        return bookParameter;
    }

    public void setBookParameter(Book bookParameter) {
        this.bookParameter = bookParameter;
    }

    public LibraryUser getSelected() {
        if (current == null) {
            current = new LibraryUser();
            selectedItemIndex = -1;
        }
        return current;
    }

    private LibraryUserFacade getFacade() {
        return ejbFacade;
    }
    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem()+getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (LibraryUser)getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new LibraryUser();
        selectedItemIndex = -1;
        return "home";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("LibraryUserCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (LibraryUser)getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public void update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("LibraryUserUpdated"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    public String destroy() {
        current = (LibraryUser)getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("LibraryUserDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count-1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex+1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public LibraryUser getLibraryUser(java.lang.Long id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass=LibraryUser.class)
    public static class LibraryUserControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LibraryUserController controller = (LibraryUserController)facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "libraryUserController");
            return controller.getLibraryUser(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof LibraryUser) {
                LibraryUser o = (LibraryUser) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: "+LibraryUser.class.getName());
            }
        }

    }
    
    
    public String login() {
        List<LibraryUser> users = ejbFacade.findAll();
        
        for (LibraryUser user : users) {
            if (user.getUsername().equals(current.getUsername()) && 
                user.getPassword().equals(current.getPassword())) {
                current = user;
                JsfUtil.addSuccessMessage("V-ați autentificat cu succes.");
                return "home";
            }
        }
        
        current = null;
        JsfUtil.addErrorMessage("Numele de utilizator și/sau parola sunt greșite.");
        return "index";
    }
    
    public String addBookToCart() {
        logger.info("Entering addBookToCart...");
//
//        Map<String,String> params =
//        FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//	String action = params.get("bookParameter");
//        logger.log(Level.INFO, "bookParam={0}", action);

        if (current.getCart() == null) {
            addCartToUser();
        }
        
        Collection<Book> books = current.getCart().getBooks();
        
        if (books == null) {
            books = new ArrayList<>();
        }
        
        logger.info(bookParameter.toString());
        books.add(bookParameter);
        current.getCart().setBooks(books);
        new CartController().update(current.getCart());
        
        logger.info("Exiting addBookToCart...");
        return "home";
    }
    
    public void addCartToUser() {
        logger.info("Entering addCartToUser...");
        CartController cartController = new CartController();
        
        // Create cart for current user.
        cartController.getSelected().setUser(current);
        Cart cart = cartController.create();
        
        current.setCart(cart);
        this.update();
        logger.info("Exiting addCartToUser...");
    }
}
