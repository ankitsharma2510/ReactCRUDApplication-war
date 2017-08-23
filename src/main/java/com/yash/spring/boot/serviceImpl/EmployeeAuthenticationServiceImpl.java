package com.yash.spring.boot.serviceImpl;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.InitialDirContext;
import javax.naming.ldap.InitialLdapContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.spring.boot.model.Employee;
import com.yash.spring.boot.model.EmployeeLoginAuthentication;
import com.yash.spring.boot.repository.EmployeeRepository;
import com.yash.spring.boot.service.EmployeeAuthenticationService;

@Service
public class EmployeeAuthenticationServiceImpl implements EmployeeAuthenticationService {

	@Autowired
	EmployeeLoginAuthentication employeeLoginObject;

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public Employee saveEmployeeData(Employee employee) {

		Employee emp = employeeRepository.save(employee);
		if (emp == null)
			throw new RuntimeException("Error while saving employee");
		return emp;
	}

	@Override
	public List<Employee> showEmployeeData() {
		return (List<Employee>) employeeRepository.findAll();
	}

	@Override
	public boolean updateEmployeeData(Employee employee) {
		Employee emp = employeeRepository.save(employee);
		if (emp == null)
			throw new RuntimeException("Error while saving employee");
		return true;
	}

	@Override
	public boolean deleteEmployeeData(Employee employee) {
		if (employee != null) {
			employeeRepository.delete(employee);
			return true;
		}
		return false;
	}

	@Override
	public boolean authenticateEmployee(EmployeeLoginAuthentication employeeLoginAuthentication) {

		InitialDirContext initialDirContext = checkUser(employeeLoginAuthentication);

		if (initialDirContext == null)
			return false;
		else
			return true;

		/************************************************************************************
		 * If you want to fetch data from ldap server then you can proceed with
		 * this commented code*
		 ************************************************************************************/

		// // retrieve the substring before the '@'
		// String email =
		// employeeLoginAuthentication.getEmployeeLoginId().substring(0,
		// employeeLoginAuthentication.getEmployeeLoginId().indexOf("@"));
		// try {
		// fetchAttributes(initialDirContext, email);
		// } catch (NamingException e) {
		// e.printStackTrace();
		// }
		// return false;
	}

	private InitialDirContext checkUser(EmployeeLoginAuthentication loginForm) {

		InitialDirContext intialDirContext = null;
		String ldapAdServer = "ldap://inidradc01.yash.com/";
		Hashtable<String, Object> environmentHashTable = new Hashtable<String, Object>();
		environmentHashTable.put(Context.SECURITY_AUTHENTICATION, "simple");

		if (null != loginForm.getEmployeeLoginId())
			environmentHashTable.put(Context.SECURITY_PRINCIPAL, loginForm.getEmployeeLoginId());
		if (null != loginForm.getPassword())
			environmentHashTable.put(Context.SECURITY_CREDENTIALS, loginForm.getPassword());

		environmentHashTable.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		environmentHashTable.put(Context.PROVIDER_URL, ldapAdServer);
		try {
			intialDirContext = new InitialLdapContext(environmentHashTable, null);
			return intialDirContext;
		} catch (NamingException e) {
			return intialDirContext;
		}
	}

	// public EmployeeLoginAuthentication fetchAttributes(InitialDirContext ctx,
	// String name) throws NamingException {
	//
	// SearchResult srLdapUser = findAccountByAccountName(ctx, "DC=yash,DC=com",
	// name);
	// Attributes attributes = srLdapUser.getAttributes();
	// if (attributes == null) {
	// System.out.println("No attributes");
	// } else {
	// Object username = attributes.get("name").get();
	// Object managerName = attributes.get("manager").get();
	// Object userId = attributes.get("description").get();
	// Object userAlias = attributes.get("mailNickName").get();
	// Object userEmail = attributes.get("mail").get();
	// Object userOffice = attributes.get("physicalDeliveryOfficeName").get();
	// Object userMobile = attributes.get("mobile").get();
	// Object userTitle = attributes.get("title").get();
	// Object userDepartment = attributes.get("department").get();
	//
	// String manager = ((String) managerName).substring(((String)
	// managerName).indexOf("=") + 1,
	// ((String) managerName).indexOf(",", 3));
	//
	// String[] managerArray = manager.split(" ");
	// String firstNamefirstLetterInCapital = managerArray[0].substring(0,
	// 1).toLowerCase();
	// String lastNameFirstLetterInCapital = managerArray[1].substring(0,
	// 1).toLowerCase();
	//
	// String managerEmailName = firstNamefirstLetterInCapital +
	// managerArray[0].substring(1) + "."
	// + lastNameFirstLetterInCapital + managerArray[1].substring(1);
	//
	// SearchResult managerLdapUser = findAccountByAccountName(ctx,
	// "DC=yash,DC=com", managerEmailName);
	//
	// Attributes managerAttributes = managerLdapUser.getAttributes();
	//
	// if (managerAttributes == null) {
	// System.out.println("No attributes");
	// }
	//
	// else {
	// Object managerId = managerAttributes.get("description").get();
	// employeeLoginObject.setName(((String) username));
	// employeeLoginObject.setEmail(((String) userEmail));
	// employeeLoginObject.setId(Long.parseLong(((String) userId)));
	// return employeeLoginObject;
	//
	// }
	//
	// }
	// return employeeLoginObject;
	//
	// }

	// public SearchResult findAccountByAccountName(InitialDirContext ctx,
	// String ldapSearchBase, String name)
	// throws NamingException {
	//
	// String searchFilter = "sAMAccountName=" + name;
	//
	// SearchControls searchControls = new SearchControls();
	//
	// searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
	//
	// NamingEnumeration<SearchResult> results = ctx.search(ldapSearchBase,
	// searchFilter, searchControls);
	//
	// SearchResult searchResult = null;
	// try {
	// if (results.hasMoreElements()) {
	// searchResult = (SearchResult) results.nextElement();
	// if (results.hasMoreElements()) {
	// System.err.println("Matched multiple users for the accountName: " +
	// name);
	// return null;
	// }
	// }
	//
	// } catch (Exception e) {
	// System.out.println(results.toString());
	// ctx.close();
	// e.printStackTrace();
	// }
	// return searchResult;
	// }

}
