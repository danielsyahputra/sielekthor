package apap.tugas.sielekthor.service;

import apap.tugas.sielekthor.model.TipeModel;

import java.util.List;

public interface TipeService {
    void addTipe(TipeModel tipe);
    List<TipeModel> getListTipe();
    TipeModel getTipeByIdTipe(Long idTipe);
    TipeModel getTipeByNamaTipe(String namaTipe);
}
