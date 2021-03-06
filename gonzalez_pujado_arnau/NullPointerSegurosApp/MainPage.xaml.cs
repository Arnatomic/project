﻿using NullPointerSegurosApp.BD;
using NullPointerSegurosApp.Controls;
using NullPointerSegurosApp.Model;
using NullPointerSegurosApp.Pages;
using System;
using System.Threading;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using System.Threading.Tasks;
using Windows.Foundation;
using Windows.Foundation.Collections;
using Windows.Graphics.Display;
using Windows.UI.ViewManagement;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Controls.Primitives;
using Windows.UI.Xaml.Data;
using Windows.UI.Xaml.Input;
using Windows.UI.Xaml.Media;
using Windows.UI.Xaml.Navigation;

// La plantilla de elemento Página en blanco está documentada en http://go.microsoft.com/fwlink/?LinkId=402352&clcid=0x409

namespace NullPointerSegurosApp
{
    /// <summary>
    /// Página vacía que se puede usar de forma independiente o a la que se puede navegar dentro de un objeto Frame.
    /// </summary>
    public sealed partial class MainPage : Page
    {

      
       
        public MainPage()
        {
            this.InitializeComponent();
            Loaded += MainPage_Loaded;

            

            
        }

        private void MainPage_Loaded(object sender, RoutedEventArgs e)
        {
            frameSinistresDetall.Navigate(typeof(AgendaPage),"");

        }

     

       

     
    }
}
