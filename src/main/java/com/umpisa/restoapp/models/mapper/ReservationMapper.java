package com.umpisa.restoapp.models.mapper;

import com.umpisa.restoapp.models.Reservation;
import com.umpisa.restoapp.models.ViewReservation;
import org.mapstruct.Builder;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Collection;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        builder = @Builder(disableBuilder = true))
public interface ReservationMapper extends  BaseMapper<ViewReservation, Reservation>{

    ReservationMapper INSTANCE = getMapper(ReservationMapper.class);

    Collection<ViewReservation> list(Collection<Reservation> entity);
    @Override
    ViewReservation toEntity(Reservation entity);

    @InheritInverseConfiguration
    Reservation toEntity(ViewReservation dto);
}
