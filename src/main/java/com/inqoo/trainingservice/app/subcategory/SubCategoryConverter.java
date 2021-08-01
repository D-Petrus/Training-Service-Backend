package com.inqoo.trainingservice.app.subcategory;

public class SubCategoryConverter {
    public SubCategoryDTO entityToDTO(Subcategory subcategory){
        SubCategoryDTO subCategoryDTO = new SubCategoryDTO(
                subcategory.getName(),
                subcategory.getDescription(),
                subcategory.getSubCategoryUUID()
        );
        return subCategoryDTO;
    }
    public Subcategory dtoToEntity(SubCategoryDTO subCategoryDTO){
        Subcategory subCategory = new Subcategory();
        subCategory.setName(subCategoryDTO.getName());
        subCategory.setDescription(subCategoryDTO.getDescription());
        subCategory.setSubCategoryUUID(subCategoryDTO.getSubCategoryUUID());
        return subCategory;
    }
}
