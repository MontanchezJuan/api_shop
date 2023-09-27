package com.shop.shop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.shop.shop.models.Client;
import com.shop.shop.repositories.ClientRepository;

@CrossOrigin
@RestController
@RequestMapping("api/clients") // Ruta para el controlador de clientes
public class ClientController {

    @Autowired
    private ClientRepository clientRepository; // Cambiado a ClientRepository

    @GetMapping("")
    public List<Client> index() {
        return this.clientRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Client store(@RequestBody Client newClient) {
        // Lógica para crear un nuevo cliente
        return this.clientRepository.save(newClient);
    }

    @GetMapping("{id}")
    public Client show(@PathVariable String id) {
        Client client = this.clientRepository
                .findById(id)
                .orElse(null);
        return client;
    }

    @PutMapping("{id}")
    public Client update(@PathVariable String id, @RequestBody Client theNewClient) {
        Client theActualClient = this.clientRepository.findById(id).orElse(null);
        if (theActualClient != null) {
            // Actualizar el cliente con los datos proporcionados
            return this.clientRepository.save(theActualClient);
        } else {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void destroy(@PathVariable String id) {
        Client theActualClient = this.clientRepository.findById(id).orElse(null);
        if (theActualClient != null) {
            this.clientRepository.delete(theActualClient);
        }
    }
}
