package com;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmpManagement 
{
	public static void main(String[] args) throws Exception 
	{
		List<String> employeeRecords = readEmployeeRecords();

		Scanner scanner = new Scanner(System.in);

		for (;;) 
		{
			System.out.print("Choose The Option" + "\n" + "-----------------" + "\n");
			System.out.println("1. Add Employee\n2. Display Employee\n3. Update Employee\n4. Delete Employee\n5. Exit");
			System.out.print("-----------------" + "\n" + "Enter your choice: " + "\n");

			switch (scanner.nextInt())
			{
			case 1:
				System.out.print("Enter Employee ID: ");
				String id = scanner.next();

				if (employeeRecords.size() > 0) 
				{
					boolean idExists = false;

					for (int i = 0; i < employeeRecords.size(); i++) 
					{
						String record1 = employeeRecords.get(i);
						String[] parts = record1.split(" ");

						if (parts.length >= 1 && parts[0].equals(id)) 
						{
							idExists = true;
						}
					}

					if (idExists) 
					{
						System.out.println("--------------");
						System.out.println("Employee ID " + id + " already exists.");
						System.out.println("--------------");
						break;
					} 
					else 
					{
						System.out.print("Enter Employee Name: ");
						String name = scanner.next();

						System.out.print("Enter Employee salary: ");
						String department = scanner.next();

						String employeeRecord = id + " " + name + " " + department;
						employeeRecords.add(employeeRecord);

						FileWriter fileWriter = new FileWriter("Employee.txt");
						
						for (String record : employeeRecords) 
						{
							fileWriter.write(record);
							fileWriter.write(System.lineSeparator());
						}
						fileWriter.close();

						System.out.println("--------------");
						System.out.println("Employee added successfully.");
						System.out.println("--------------");
						break;
					}
				} 
				else 
				{
					System.out.print("Enter Employee Name: ");
					String name = scanner.next();

					System.out.print("Enter Employee salary: ");
					String department = scanner.next();

					String employeeRecord = id + " " + name + " " + department;
					employeeRecords.add(employeeRecord);

					FileWriter fileWriter = new FileWriter("Employee.txt");
					
					for (String record : employeeRecords) 
					{
						fileWriter.write(record);
						fileWriter.write(System.lineSeparator());
					}
					fileWriter.close();

					System.out.println("--------------");
					System.out.println("Employee added successfully.");
					System.out.println("--------------");
					break;
				}

			case 2:
				File file = new File("Employee.txt");

				if (file.exists() && file.length() > 0) 
				{
					FileReader fileReader = new FileReader(file);
					
					int i;
					
					System.out.println("--- Employee List ---");

					while ((i = fileReader.read()) != -1) 
					{
						System.out.print((char) i);
					}
					fileReader.close();
					System.out.println("---------------------");
				} 
				else 
				{
					System.out.println("----------------");
					System.out.println("No data available.");
					System.out.println("----------------");
				}
				break;

			case 3:
				System.out.print("Enter the ID of the employee to Update: ");
				String idToUpdate = scanner.next();

				boolean updated = false;
				for (int e = 0; e < employeeRecords.size(); e++) 
				{
					String record = employeeRecords.get(e);
					if (record.startsWith(idToUpdate + " ")) 
					{
						System.out.print("Enter new employee name:");
						String newName = scanner.next();

						System.out.print("Enter new employee salary:");
						String newSalary = scanner.next();

						String updatedRecord = idToUpdate + " " + newName + " " + newSalary;
						employeeRecords.set(e, updatedRecord);
						updated = true;
						break;
					}
				}

				if (updated) 
				{
					System.out.println("--------------");
					System.out.println("Employee updated successfully.");
					System.out.println("--------------");

					FileWriter fileWriter1 = new FileWriter("Employee.txt");
					for (String record : employeeRecords) 
					{
						fileWriter1.write(record);
						fileWriter1.write(System.lineSeparator());
					}
					fileWriter1.close();
				} 
				else 
				{
					System.out.println("--------------");
					System.out.println("Employee record with ID " + idToUpdate + " not found.");
					System.out.println("--------------");
				}
				break;

			case 4:
				System.out.print("Enter the ID of the employee to Delete: ");
				String idToDelete = scanner.next();

				boolean found = false;
				for (int e = employeeRecords.size() - 1; e >= 0; e--) 
				{
					String record = employeeRecords.get(e);
					if (record.startsWith(idToDelete + " ")) 
					{
						employeeRecords.remove(e);
						found = true;
						break;
					}
				}

				FileWriter fileWriter1 = new FileWriter("Employee.txt");
				for (String record : employeeRecords) 
				{
					fileWriter1.write(record);
					fileWriter1.write(System.lineSeparator());
				}
				fileWriter1.close();

				if (found) 
				{
					System.out.println("--------------");
					System.out.println("Employee deleted successfully.");
					System.out.println("--------------");
				} 
				else 
				{
					System.out.println("--------------");
					System.out.println("Employee with ID " + idToDelete + " not found.");
					System.out.println("--------------");
				}
				break;
				
			case 5:
				System.out.println("--------------");
				System.out.println("Exited");
				System.out.println("--------------");
				System.exit(0);
				
			default:
				System.out.println("--------------");
				System.out.println("You have entered wrong choice.");
				System.out.println("--------------");
				break;
			}
		}
	}

	private static List<String> readEmployeeRecords() throws FileNotFoundException 
	{
		List<String> employeeRecords = new ArrayList<>();

		File file = new File("Employee.txt");
		if (file.exists()) 
		{
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) 
			{
				String line = scanner.nextLine();
				employeeRecords.add(line);
			}
			scanner.close();
		}
		return employeeRecords;
	}
}
