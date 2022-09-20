package interfaces;

import java.util.Collection;
import java.util.List;

public interface Converter<Entity, EntityDTO>
{
        public EntityDTO toDTO(Entity entity);
        public List<EntityDTO> toDTO(Collection<Entity> entity);
        public Entity toEntity(EntityDTO EntityDTO);
}
