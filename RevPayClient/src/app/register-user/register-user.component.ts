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
  username = ""
  email = ""
  phoneNumber= ""
  password= ""
  confirmPassword= ""
  httpClient:HttpClient;
  currentUser:CurrentUserService;
  constructor(httpClient:HttpClient, currentUser:CurrentUserService){
    this.httpClient=httpClient;
    this.currentUser=currentUser;
  }
  
  OnClickSubmit(){
    console.log("Username:" + this.username)
    this.currentUser.setUsername(this.username);
    // let response = this.httpClient.get("http://localhost:8080/users/username/testUser")
    // response.subscribe((data)=>console.log(data))
  }
}
