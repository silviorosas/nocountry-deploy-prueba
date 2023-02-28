package com.nocountry.controllers;

import com.nocountry.models.Rol;
import com.nocountry.models.Usuario;
import com.nocountry.models.UsuarioRol;
import com.nocountry.services.UsuarioService;

import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//Controller posee los end point para USUARIOS
@RestController
@RequestMapping(value="/usuarios/")
@CrossOrigin(origins = "http://localhost")
public class UsuarioController {


    private final   UsuarioService usuarioService;
  


    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;

    }


    //End point para guardar usuarios y su rol POST="/usuarios/"
    @PostMapping
    public Usuario guardarUsuario(@RequestBody Usuario usuario) throws Exception{

       

        Set<UsuarioRol> usuarioRoles = new HashSet<>();

        Rol rol = new Rol();
        rol.setRolId(2L);
        rol.setRolNombre("NORMAL");

        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);

        usuarioRoles.add(usuarioRol);

        return usuarioService.saveUsuario(usuario,usuarioRoles);
    }

    //End point para obtener por username usuarios GET="/usuarios/s@gmail.com"
    @GetMapping("username/{username}")
    public Usuario obtenerUsuario(@PathVariable("username") String username){
        return usuarioService.getUsuario(username);
    }


    //End point para eliminar por id usuarios DELETE="/usuarios/2"
    @DeleteMapping("{usuarioId}")
    public void eliminarUsuario(@PathVariable("usuarioId") Long usuarioId){
        usuarioService.deleteUsuario(usuarioId);
    }

    //End point para obtener por id  usuarios GET="/usuarios/2"
    @GetMapping("{id}")
    public Optional<Usuario> buscarById(@PathVariable Long id) throws EntityNotFoundException {
        return usuarioService.searchById(id);
    }

    //End point para obtener todos los  usuarios GET="/usuarios/"
    @GetMapping
    public List<Usuario> getAll(){
        return usuarioService.getAllUsuarios();
    }


}
