package com.team_ingterior.ingterior.photo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.team_ingterior.ingterior.photo.domain.InsertPhotoDTO;
import com.team_ingterior.ingterior.photo.domain.PhotoObjectDTO;
import com.team_ingterior.ingterior.photo.domain.PhotoResponseDTO;

@Mapper
public interface PhotoMapper {
    void insertPhoto(InsertPhotoDTO photo);
    List<PhotoResponseDTO> getPhotosByObject(PhotoObjectDTO object);
    PhotoResponseDTO getPhotoByPhotoId(int photoId);
    void deletePhotoByPhotoId(int photoId);
}
