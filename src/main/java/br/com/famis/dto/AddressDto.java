package br.com.famis.dto;

import lombok.Data;

@Data
public class AddressDto {

    private String cep;

    private String place; //rua

    private String number;

    private String district; //bairro

    private String city;

    private String state;

    public AddressDto(String cep, String place, String number, String district, String city, String state) {
        this.cep = cep;
        this.place = place;
        this.number = number;
        this.district = district;
        this.city = city;
        this.state = state;
    }

    public AddressDto() {
    }
}
