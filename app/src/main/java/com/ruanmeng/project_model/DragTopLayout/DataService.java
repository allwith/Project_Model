package com.ruanmeng.project_model.DragTopLayout;

import java.util.ArrayList;
import java.util.List;

import github.chenupt.multiplemodel.ItemEntity;
import github.chenupt.multiplemodel.ItemEntityCreator;
import github.chenupt.multiplemodel.ModelManager;
import github.chenupt.multiplemodel.ModelManagerBuilder;

/**
 * Created by chenupt@gmail.com on 1/30/15.
 * Description :
 */
public class DataService {



    public ModelManager getModelManager() {
        return ModelManagerBuilder.begin().addModel(CustomView.class).build(ModelManager.class);
    }

    public List<ItemEntity> getList() {
        List<ItemEntity> resultList = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            ItemEntityCreator.create("").setModelView(CustomView.class).attach(resultList);
        }
        return resultList;
    }




    private static volatile DataService instance = null;

    private DataService(){
    }

    public static DataService getInstance() {
        if (instance == null) {
            synchronized (DataService.class) {
                if (instance == null) {
                    instance = new DataService();
                }
            }
        }
        return instance;
    }

}
