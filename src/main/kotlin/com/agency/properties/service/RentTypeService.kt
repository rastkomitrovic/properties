package com.agency.properties.service

import com.agency.properties.dto.RentTypeDTO
import com.agency.properties.mapper.RentTypeMapper
import com.agency.properties.repository.RentTypeRepository
import com.agency.properties.util.AgencyException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class RentTypeService @Autowired constructor(
        private val rentTypeRepository: RentTypeRepository,
        private val rentTypeMapper: RentTypeMapper
){
    fun saveRentType(rentTypeDTO: RentTypeDTO) :RentTypeDTO{
        if(rentTypeDTO.rentTypeId!=null && rentTypeRepository.existsById(rentTypeDTO.rentTypeId))
            throw AgencyException("Rent type with the passed id:${rentTypeDTO.rentTypeId} already exists!")
        return rentTypeMapper.mapToDto(rentTypeMapper.mapToEntity(rentTypeDTO))
    }

    fun updateRentType(rentTypeDTO: RentTypeDTO) :RentTypeDTO{
        if(rentTypeDTO.rentTypeId!=null && !rentTypeRepository.existsById(rentTypeDTO.rentTypeId))
            throw AgencyException("Rent type with the passed id:${rentTypeDTO.rentTypeId} doesn't exists!")
        return rentTypeMapper.mapToDto(rentTypeMapper.mapToEntity(rentTypeDTO))
    }

    fun deleteRentTypeById(id:Long){
        if(!rentTypeRepository.existsById(id))
            throw AgencyException("Rent type with the passed id:${id} doesn't exists!")
        rentTypeRepository.deleteById(id)
    }

    fun findAll():List<RentTypeDTO>{
        return rentTypeMapper.mapListToDto(rentTypeRepository.findAll())
    }

    fun saveAll(list:List<RentTypeDTO>):List<RentTypeDTO>{
        list.forEach { if(it.rentTypeId!=null && rentTypeRepository.existsById(it.rentTypeId)) throw AgencyException("Rent type with the passed id:${it.rentTypeId} already exists!")}
        return rentTypeMapper.mapListToDto(rentTypeRepository.findAll())
    }
}