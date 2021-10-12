package apap.tugas.sielekthor.service;

import apap.tugas.sielekthor.model.TipeModel;
import apap.tugas.sielekthor.repository.TipeDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TipeServiceImpl implements TipeService{
    @Autowired
    TipeDb tipeDb;

    @Override
    public void addTipe(TipeModel tipe) {
        tipeDb.save(tipe);
    }

    @Override
    public List<TipeModel> getListTipe() {
        return tipeDb.findAll();
    }
}
