import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CurrentUserService } from '../current-user.service';
@Component({
  selector: 'app-register-user',
  standalone: true,
  imports: [FormsModule, HttpClientModule],
  templateUrl: './register-user.component.html',
  styleUrl: './register-user.component.css'
})
export class RegisterUserComponent {
  username: string;
  email: string;
  phoneNumber: string;
  password: string;
  confirmPassword: string;
  httpClient:HttpClient;
  currentUser:CurrentUserService;
  constructor(httpClient:HttpClient, currentUser:CurrentUserService){
    console.log("register user component")
    this.httpClient=httpClient;
    this.currentUser=currentUser;
    this.username = currentUser.getUsername();
    this.email = "";
    this.phoneNumber = "";
    this.password = "";
    this.confirmPassword = "";
  }
  
  OnClickSubmit(){
    console.log("Username:" + this.username)
    this.currentUser.setUsername(this.username);
    // let response = this.httpClient.get("http://localhost:8080/users/username/testUser")
    // response.subscribe((data)=>console.log(data))
  }
}
