package br.com.famis.mapper;

import br.com.famis.Dto.AddressDto;
import br.com.famis.model.Address;

import java.util.List;
import java.util.stream.Collectors;

public class AddressMapper {

    public AddressDto addressToDto(Address address){
        AddressDto addressDto = new AddressDto();
        addressDto.setCep(address.getCep());
        addressDto.setPlace(address.getPlace());
        addressDto.setDistrict(address.getDistrict());
        addressDto.setCity(address.getPlace());
        addressDto.setState(address.getState());

        return addressDto;
    }

    public Address dtoToAddress( AddressDto addressDto){
        Address address = new Address();
        address.setCep(addressDto.getCep());
        address.setPlace(addressDto.getPlace());
        address.setDistrict(addressDto.getDistrict());
        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());

        return address;
    }

    public List<AddressDto> addressesToDtos (List<Address> addresses){
        List<AddressDto> addressesDtos;
        addressesDtos = addresses.stream().map(this::addressToDto).collect(Collectors.toList());
        return addressesDtos;
    }

    public List<Address> dtosToAddresses (List<AddressDto> addressesDtos){
        List<Address> addresses;
        addresses = addressesDtos.stream().map(this::dtoToAddress).collect(Collectors.toList());
        return addresses;
    }
}
