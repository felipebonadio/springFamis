package br.com.famis.Dto;

import lombok.Data;

@Data
public class AddressDto {

    private String cep;

    private String place; //rua

    private String district; //bairro

    private String city;

    private String state;

    public AddressDto(String cep, String place, String district, String city, String state) {
        this.cep = cep;
        this.place = place;
        this.district = district;
        this.city = city;
        this.state = state;
    }

    public AddressDto() {
    }
}
