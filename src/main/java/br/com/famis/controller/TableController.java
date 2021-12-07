package br.com.famis.controller;

import br.com.famis.model.Table;
import br.com.famis.service.FamisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/tables")
public class TableController {

    @Autowired
    private FamisService famisService;

    @RequestMapping(value="/tableId", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Table> getTable(@PathVariable("tableId") UUID tableId){
        Table table = this.famisService.findTableById(tableId);
        if (table == null){
            return new ResponseEntity<Table>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Table>(table, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Table>> getTables(){
        List<Table> tables = famisService.findAllTables();
        if(tables.isEmpty()){
            return new ResponseEntity<List<Table>>( HttpStatus.NOT_FOUND );
        }
        return new ResponseEntity<List<Table>>(tables, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Table> saveTable(@RequestBody Table table, BindingResult bindingResult) {
        if(bindingResult.hasErrors() || (table == null) || (table.getNumber() == null)){
            return new ResponseEntity<Table>(table, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Table>(famisService.saveTable(table), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{tableId}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Table> updateTable(@PathVariable("tableId") UUID tableId, @RequestBody Table table, BindingResult bindingResult){
        if(bindingResult.hasErrors() || (table == null) || (table.getNumber() == null)){
            return new ResponseEntity<Table>(table, HttpStatus.BAD_REQUEST);
        }
        Table updatedTable = this.famisService.updateTable(tableId, table);

        if(updatedTable == null){
            return new ResponseEntity<Table>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Table>(updatedTable, HttpStatus.OK);
    }

    @RequestMapping(value = "/{tableId}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Table> deleteById(@PathVariable("tableId") UUID tableId) {
        Table table = this.famisService.findTableById(tableId);
        famisService.deleteTable(table);
        if (table == null) {
            return new ResponseEntity<Table>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Table>(table, HttpStatus.OK);
    }
}
