import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router'; 
import { FormsModule, ReactiveFormsModule  } from '@angular/forms'; 
import { CommonModule } from '@angular/common';
import { Formscarro } from 'src/formscarro/formscarro.component';
import { CarroComponent } from 'src/cadastrocarro/cadastrocarro.component';
import { Carregando } from 'src/carregando/carregando.component';
import { EditarCarroComponent } from 'src/editar-carros/editar-carros.component';


@NgModule({
  declarations: [
    AppComponent,
    Formscarro,
    CarroComponent,
    Carregando,
    EditarCarroComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule, 
    FormsModule, 
    ReactiveFormsModule,
    CommonModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
