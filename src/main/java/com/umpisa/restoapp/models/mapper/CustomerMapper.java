package com.umpisa.restoapp.models.mapper;

import com.umpisa.restoapp.models.Customer;
import com.umpisa.restoapp.models.ViewCustomer;
import org.mapstruct.Builder;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Collection;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        builder = @Builder(disableBuilder = true))
public interface CustomerMapper extends  BaseMapper<ViewCustomer, Customer>{

    CustomerMapper INSTANCE = getMapper(CustomerMapper.class);

    Collection<ViewCustomer> list(Collection<Customer> entity);
    @Override
    ViewCustomer toEntity(Customer entity);

    @InheritInverseConfiguration
    Customer toEntity(ViewCustomer dto);
}
