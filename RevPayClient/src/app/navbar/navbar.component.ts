import { Component } from '@angular/core';
import { ShowTransactionsComponent } from '../show-transactions/show-transactions.component';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [ShowTransactionsComponent, RouterOutlet],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

}
