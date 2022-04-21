/**
 * 
 */
package com.stackroute.crud.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.crud.util.Create;
import com.stackroute.crud.util.Delete;
import com.stackroute.crud.util.Read;
import com.stackroute.crud.util.Update;

/**
 * @author 91994
 *
 */
/**
 * @author 91994
 *
 */
@RestController
@RequestMapping("/crud")
public class SpringCrudController {

	@ResponseBody
	@GetMapping("/healthcheck")
	public ResponseEntity<Object> healthCheck() {

		return new ResponseEntity<Object>("{\"status\":\"Spring Example Service is working\"}", HttpStatus.ACCEPTED);
	}

	@ResponseBody
	@GetMapping("/create")
	public ResponseEntity<Object> addEmployee(@RequestParam Map<String, String> params) {
		String name = params.get("name");
		String age = params.get("age");
		String gender = params.get("gender");
		Create create = new Create();
		try {
			Integer id = create.createOperation(name, Integer.parseInt(age), gender);
			if (id != null) {
				return new ResponseEntity<Object>(
						"{\"status\":\"Record Added Successfully\"," + "\"employeeId\":" + id + "}",
						HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<Object>("{\"status\":\"Something went wrong\"}",
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception excep) {
			return new ResponseEntity<Object>("{\"status\":\"Something went wrong\"}",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ResponseBody
	@GetMapping("/update")
	public ResponseEntity<Object> updateEmployee(@RequestParam Map<String, String> params) {
		String name = params.get("name");
		String age = params.get("age");
		String gender = params.get("gender");
		String id = params.get("id");
		Update update = new Update();
		try {
			update.updateOperation(Integer.parseInt(id), name, Integer.parseInt(age), gender);
			if (id != null) {
				return new ResponseEntity<Object>("{\"status\":\"Record Updated Successfully\"}", HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<Object>("{\"status\":\"Something went wrong\"}",
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception excep) {
			return new ResponseEntity<Object>("{\"status\":\"Something went wrong\"}",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ResponseBody
	@GetMapping("/delete")
	public ResponseEntity<Object> removeEmployee(@RequestParam("id") Integer id) {
		Delete delete = new Delete();
		delete.deleteOperation(id);
		return new ResponseEntity<Object>("{\"status\":\"Employee ID " + id + " Deleted Successfully\"}",
				HttpStatus.ACCEPTED);
	}

	/**
	 * @param id
	 * @return
	 */
	@ResponseBody
	@GetMapping("/read")
	public ResponseEntity<Object> getEmployee(@RequestParam("id") Integer id) {
		Read read = new Read();
		Map<String, String> response = read.readOperation(id);
		return new ResponseEntity<Object>(response, HttpStatus.ACCEPTED);
	}
}
