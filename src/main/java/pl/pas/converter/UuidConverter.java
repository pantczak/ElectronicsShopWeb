package pl.pas.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.UUID;

@FacesConverter("UUIDConverter")
public class UuidConverter implements Converter<UUID> {

    @Override
    public UUID getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.equals(""))
        {
            return null;
        }
        return UUID.fromString(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, UUID value) {
        if (value == null){
            return null;
        }
        return value.toString();
    }
}
