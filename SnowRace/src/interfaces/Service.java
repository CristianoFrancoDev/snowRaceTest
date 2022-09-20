package interfaces;

import java.util.List;

public interface Service<DTO>
{
    public DTO read(int id);
    public boolean insert(DTO dto);
    public boolean update(DTO dto);
    public boolean delete(int id);
    public List<DTO> getAll();
}
